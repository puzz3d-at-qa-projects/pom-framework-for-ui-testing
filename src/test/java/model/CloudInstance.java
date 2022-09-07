package model;

import java.util.Objects;

public class CloudInstance {

    private final String numberOfInstances;
    private final String instanceType;
    private final String instanceTypeLocator;
    private final String instanceLocation;
    private final String instanceLocationLocator;

    public CloudInstance(String numberOfInstances, String instanceType, String instanceLocation) {
        this.numberOfInstances = numberOfInstances;
        this.instanceType = instanceType;
        this.instanceTypeLocator = "//div[contains(text()," +
                instanceType.replaceAll("^\"|\"$", "'")
                + ")]";
        this.instanceLocation = instanceLocation;
        this.instanceLocationLocator = "//div[contains(@class,'md-clickable')]//div[contains(text()," +
                instanceLocation.replaceAll("^\"|\"$", "'") +
                ")]";
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public String getInstanceLocationLocator() {
        return instanceLocationLocator;
    }

    public String getInstanceTypeLocator() {
        return instanceTypeLocator;
    }

    public String getInstanceType() {
        return instanceType;
    }
    public String getInstanceLocation() {
        return instanceLocation;
    }

    @Override
    public String toString() {
        return "CloudInstance{" +
                "numberOfInstances='" + numberOfInstances + '\'' +
                ", instanceType='" + instanceType + '\'' +
                ", instanceLocation='" + instanceLocation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloudInstance that = (CloudInstance) o;
        return Objects.equals(numberOfInstances, that.numberOfInstances) && Objects.equals(instanceType, that.instanceType) && Objects.equals(instanceLocation, that.instanceLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfInstances, instanceType, instanceLocation);
    }
}
