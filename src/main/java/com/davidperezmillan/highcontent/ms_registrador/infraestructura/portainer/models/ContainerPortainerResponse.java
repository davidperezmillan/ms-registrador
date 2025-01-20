// Container.java
package com.davidperezmillan.highcontent.ms_registrador.infraestructura.portainer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ContainerPortainerResponse {
    @JsonProperty("Command")
    private String command;

    @JsonProperty("Created")
    private long created;

    @JsonProperty("HostConfig")
    private HostConfig hostConfig;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("Image")
    private String image;

    @JsonProperty("ImageID")
    private String imageID;

    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonProperty("Mounts")
    private List<Mount> mounts;

    @JsonProperty("Names")
    private List<String> names;

    @JsonProperty("NetworkSettings")
    private NetworkSettings networkSettings;

    @JsonProperty("Ports")
    private List<Port> ports;

    @JsonProperty("State")
    private String state;

    @JsonProperty("Status")
    private String status;

    @Data
    public static class HostConfig {
        @JsonProperty("NetworkMode")
        private String networkMode;
    }

    @Data
    public static class Mount {
        @JsonProperty("Destination")
        private String destination;

        @JsonProperty("Mode")
        private String mode;

        @JsonProperty("Propagation")
        private String propagation;

        @JsonProperty("RW")
        private boolean rw;

        @JsonProperty("Source")
        private String source;

        @JsonProperty("Type")
        private String type;
    }

    @Data
    public static class NetworkSettings {
        @JsonProperty("Networks")
        private Map<String, Network> networks;

        @Data
        public static class Network {
            @JsonProperty("Aliases")
            private List<String> aliases;

            @JsonProperty("DNSNames")
            private List<String> dnsNames;

            @JsonProperty("DriverOpts")
            private Map<String, String> driverOpts;

            @JsonProperty("EndpointID")
            private String endpointID;

            @JsonProperty("Gateway")
            private String gateway;

            @JsonProperty("GlobalIPv6Address")
            private String globalIPv6Address;

            @JsonProperty("GlobalIPv6PrefixLen")
            private int globalIPv6PrefixLen;

            @JsonProperty("IPAMConfig")
            private String ipamConfig;

            @JsonProperty("IPAddress")
            private String ipAddress;

            @JsonProperty("IPPrefixLen")
            private int ipPrefixLen;

            @JsonProperty("IPv6Gateway")
            private String ipv6Gateway;

            @JsonProperty("Links")
            private List<String> links;

            @JsonProperty("MacAddress")
            private String macAddress;

            @JsonProperty("NetworkID")
            private String networkID;
        }
    }

    @Data
    public static class Port {
        // Define fields for Port if needed
    }
}