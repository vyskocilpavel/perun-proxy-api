package cz.muni.ics.perunproxyapi.application.endpointproviders;

import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Developer;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InfoEndpointProvider implements InfoContributor {

    private GitInfoProperties gitInfoProperties;

    @Autowired
    private void setGitProperties(GitInfoProperties gitInfoProperties) {
        this.gitInfoProperties = gitInfoProperties;
    }

    @Override
    public void contribute(Info.Builder builder) {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        try {
            Model model = reader.read(new FileReader("pom.xml"));
            Map<String, String> versions = new LinkedHashMap<>();
            versions.put("project", model.getVersion());
            versions.put("java", model.getProperties().getProperty("java.version"));
            versions.put("spring-boot-parent", model.getParent().getVersion());

            Map<String, Object> proxyapiInfo = new LinkedHashMap<>();
            proxyapiInfo.put("GroupID", model.getGroupId());
            proxyapiInfo.put("ArtifactID", model.getArtifactId());
            proxyapiInfo.put("Git", gitInfoProperties);
            proxyapiInfo.put("Version", model.getVersion());
            proxyapiInfo.put("Name", model.getName());
            proxyapiInfo.put("Description", model.getDescription());
            proxyapiInfo.put("License", model.getLicenses());
            proxyapiInfo.put("Versions", versions);
            proxyapiInfo.put("Inception year", model.getInceptionYear());
            proxyapiInfo.put("Issue management", model.getIssueManagement());
            proxyapiInfo.put("Organization", model.getOrganization());
            proxyapiInfo.put("Developers", getDevelopers(model.getDevelopers()));

            builder.withDetail("ProxyAPI", proxyapiInfo);
        } catch (IOException | XmlPullParserException e) {
            log.warn("Exception caught when reading pom.xml", e);
        }
    }

    private List<DeveloperInfo> getDevelopers(List<Developer> developers) {
        return developers.stream()
                .map(d -> new DeveloperInfo(d.getName(), d.getEmail(),
                        d.getOrganization(), d.getOrganizationUrl(), d.getTimezone())
                ).collect(Collectors.toList());
    }

}
