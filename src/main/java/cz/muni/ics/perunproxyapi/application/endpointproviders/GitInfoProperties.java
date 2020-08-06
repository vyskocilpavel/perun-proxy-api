package cz.muni.ics.perunproxyapi.application.endpointproviders;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

@Getter
@Component
@Slf4j
public class GitInfoProperties {

    private String branch;
    private String commitId;
    private String commitIdAbbrev;
    private String commitTime;
    private String buildTime;
    private String buildVersion;

    @PostConstruct
    public void doInit() {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("git.properties");
            log.debug("Loaded git.properties");
        } catch (IOException e) {
            log.warn("Failed to load git properties from git.properties");
        }
        if (properties != null) {
            this.branch = properties.getProperty("git.branch", "");
            this.commitId = properties.getProperty("git.commit.id", "");
            this.commitIdAbbrev = properties.getProperty("git.commit.id.abbrev", "");
            this.commitTime = properties.getProperty("git.commit.time", "");
            this.buildTime = properties.getProperty("git.build.time", "");
            this.buildVersion = properties.getProperty("git.build.version", "");
        }
    }

}
