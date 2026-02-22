package com.assetguard.core.model.location;

import com.assetguard.core.model.shared.LocationType;
import com.assetguard.core.model.shared.RecordStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Location {

    @Id
    @UuidGenerator
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "parent_location_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Location parentLocation;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private LocationType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "record_status", nullable = false)
    @Builder.Default
    private RecordStatus recordStatus = RecordStatus.ACTIVE;
}
