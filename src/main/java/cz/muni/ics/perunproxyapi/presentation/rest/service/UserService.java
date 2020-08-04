package cz.muni.ics.perunproxyapi.presentation.rest.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import cz.muni.ics.perunproxyapi.presentation.rest.models.User;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserService {

    public static List<User> getUsersFromYamlFile(String path) {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

            return mapper.readValue(new File(path), new TypeReference<>() {});
        } catch (IOException e) {
            log.warn("Reading users from config was not successful: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
