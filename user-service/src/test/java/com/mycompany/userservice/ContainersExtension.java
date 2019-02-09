package com.mycompany.userservice;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;

@Testcontainers
public class ContainersExtension implements BeforeAllCallback, AfterAllCallback {

    @Container
    private static MySQLContainer mySQLContainer;

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        mySQLContainer = new MySQLContainer("mysql:5.7.24")
                .withDatabaseName("test-userdb")
                .withUsername("test-user")
                .withPassword("test-secret");

        mySQLContainer.setPortBindings(Collections.singletonList("3306:3306"));
        mySQLContainer.start();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        mySQLContainer.stop();
    }

}