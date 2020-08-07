package cz.muni.ics.perunproxyapi.application.endpointproviders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperInfo {

    private String name;
    private String email;
    private String organization;
    private String organizationURL;
    private String timezone;

}
