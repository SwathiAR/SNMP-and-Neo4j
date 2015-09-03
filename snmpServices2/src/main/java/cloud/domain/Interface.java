package cloud.domain;

import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by swathi on 8/6/2015.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class Interface extends Entity {
    @Relationship(type = "CONTAINED_INTERFACE", direction = Relationship.INCOMING)
    @Setter(AccessLevel.NONE)
    private System system;
    @Property
    private String ifIndex;
    @Setter(AccessLevel.NONE)
    @Property
    private String ifDescr;
    @Property
    private String ifType;
    @Property
    private String ifMtu;
    @Property
    private String ifSpeed;
    @Property
    private String ifPhysAddress;
    @Property
    private String ifAdminStatus;
    @Property
    private String ifOperStatus;
    @Property
    private String ifLastChange;
    @Property
    private String ifInOctets;
    @Property
    private String ifInUcastPkts;
    @Property
    private String ifInNUcastPkts;
    @Property
    private String ifInDiscards;
    @Property
    private String ifInErrors;
    @Property
    private String ifInUnknownProtos;
    @Property
    private String ifOutOctets;
    @Property
    private String ifOutUcastPkts;
    @Property
    private String ifOutNUcastPkts;
    @Property
    private String ifOutDiscards;
    @Property
    private String ifOutErrors;
    @Property
    private String ifOutQLen;
    @Property
    private String ifSpecific;
    @Setter(AccessLevel.NONE)
    @Property
    private String name;

    public void setIfDescr(String ifDescr) {
        this.ifDescr = ifDescr;
        setName();
    }

    public void setName() {
        this.name = system != null ? system.getName() + "-" + ifDescr : ifDescr;
    }

    public void setSystem(System system) {
        this.system = system;
        setName();
    }

    @Override
    public String toString() {
        return "Interface{" +
                ", ifIndex='" + ifIndex + '\'' +
                ", ifDescr='" + ifDescr + '\'' +
                ", ifType='" + ifType + '\'' +
                ", ifMtu='" + ifMtu + '\'' +
                ", ifSpeed='" + ifSpeed + '\'' +
                ", ifPhysAddress='" + ifPhysAddress + '\'' +
                ", ifAdminStatus='" + ifAdminStatus + '\'' +
                ", ifOperStatus='" + ifOperStatus + '\'' +
                ", ifLastChange='" + ifLastChange + '\'' +
                ", ifInOctets='" + ifInOctets + '\'' +
                ", ifInUcastPkts='" + ifInUcastPkts + '\'' +
                ", ifInNUcastPkts='" + ifInNUcastPkts + '\'' +
                ", ifInDiscards='" + ifInDiscards + '\'' +
                ", ifInErrors='" + ifInErrors + '\'' +
                ", ifInUnknownProtos='" + ifInUnknownProtos + '\'' +
                ", ifOutOctets='" + ifOutOctets + '\'' +
                ", ifOutUcastPkts='" + ifOutUcastPkts + '\'' +
                ", ifOutNUcastPkts='" + ifOutNUcastPkts + '\'' +
                ", ifOutDiscards='" + ifOutDiscards + '\'' +
                ", ifOutErrors='" + ifOutErrors + '\'' +
                ", ifOutQLen='" + ifOutQLen + '\'' +
                ", ifSpecific='" + ifSpecific + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
