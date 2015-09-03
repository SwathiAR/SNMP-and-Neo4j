package cloud.domain;



import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * Created by swathi on 8/6/2015.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
public class System extends Entity {
    @Property(name = "name")
    private String name;

    @Property(name = "sysDescr")
    private String sysDescr;


    @Property(name = "sysObjectID")
    private String sysObjectID;

    @Property(name = "sysUpTime")
    private String sysUpTime;

    @Property(name = "sysContact")
    private String sysContact;


    @Property(name = "sysName")
    private String sysName;

    @Property(name = "sysLocation")
    private String sysLocation;

    @Property(name = "sysServices")
    private String sysServices;

    @Relationship(type = "CONTAINED_INTERFACE")
    private Set<Interface> containedInterface;

    @Override
    public String toString() {
        return "System{" +
                "name='" + name + '\'' +
                ", sysDescr='" + sysDescr + '\'' +
                ", sysObjectID='" + sysObjectID + '\'' +
                ", sysUpTime='" + sysUpTime + '\'' +
                ", sysContact='" + sysContact + '\'' +
                ", sysName='" + sysName + '\'' +
                ", sysLocation='" + sysLocation + '\'' +
                ", sysServices='" + sysServices + '\'' +
                ", containedInterface=" + containedInterface +
                '}';
    }
}