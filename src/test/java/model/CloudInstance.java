package model;

public record CloudInstance(String numberOfInstances, String instanceType, String instanceLocation) {

    public CloudInstance(String numberOfInstances, String instanceType, String instanceLocation) {
        this.numberOfInstances = numberOfInstances;
        this.instanceType = instanceType.replaceAll("^\"|\"$", "");
        this.instanceLocation = instanceLocation.replaceAll("^\"|\"$", "");
    }
}
