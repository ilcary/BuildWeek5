package com.ilCary.EPIC_ENERGY_SERVICES.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 //   @Column(unique = true, nullable = false)

    //valori che andiamo a definire come univoci
    @Column(unique = true, nullable = false)
    private String pec;
    @Column(unique = true, nullable = false)
    private String emailContatto;
    @Column(unique = true, nullable = false)
    private String telefono;
    @Column(unique = true, nullable = false)
    private String partitaIva;
    @Column(unique = true, nullable = false)
    private String ragioneSociale;
    @Column(unique = true, nullable = false)
    private String email;

//    @OneToMany(mappedBy = "Address")
//    private List<Address> addresses;
//    
//    @OneToMany(mappedBy = "Invoice")
//    private List<Invoice> invoices;
    
    private typeClient type;
    
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private Double fatturatoAnnuale;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;

//    @ManyToMany
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//            )
//    private Set<Role> roles = new HashSet<>();

}