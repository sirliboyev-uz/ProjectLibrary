package com.example.projectlibrary.Entayti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Rents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer kitobId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String berilganVaqt;

    @Column(nullable = false)
    private Boolean berildi;

    @Column(nullable = false)
    private String olinganVaqt;

    @Column(nullable = false)
    private Boolean olindi;

    @Column(nullable = false)
    private String mudat;
}
