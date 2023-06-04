package com.assignment.ziploan.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contact")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String phone;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
