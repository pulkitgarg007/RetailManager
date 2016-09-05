package com.retail.manager.domain;

public class Geometry{
    private String location_type;

    private Location location;

    public String getLocation_type ()
    {
        return location_type;
    }

    public void setLocation_type (String location_type)
    {
        this.location_type = location_type;
    }

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [location_type = "+location_type+", location = "+location+"]";
    }
}