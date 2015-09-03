package com.torana.training.model;

/**
 * Created by swathi on 7/27/2015.
 */
public class Instance {

    private String oid;
    private String oidName;
    private String oidValue;



    public Instance(String Oid, String Name){
        this.oid = Oid;
        this.oidName = Name;
    }


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return oidName;
    }

    public void setName(String name) {
        oidName = name;
    }

    public String getOidValue() {
        return oidValue;
    }

    public void setOidValue(String oidValue) {
        this.oidValue = oidValue;
    }


}
