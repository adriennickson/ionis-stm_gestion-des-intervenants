package com.ionisstm.intervenants.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private int id;

    @Column(name = "street")
    @NotNull(message = "*Please provide a street")
    @NotEmpty(message = "*Please provide a street")
    private String street;

    @Column(name = "line")
    @NotNull(message = "*Please provide a line adress")
    @NotEmpty(message = "*Please provide a line adress")
    private String line;

    @Column(name = "post_code", length = 5)
    @NotNull(message = "*Please provide a post code")
    @NotEmpty(message = "*Please provide a post code")
    @Pattern(regexp="^\\d{5}$", message="Please provide 5 numbers")
    private String postCode;

    @Column(name = "city")
    @NotNull(message = "*Please provide city")
    @NotEmpty(message = "*Please provide city")
    private String city;

    @Column(name = "line_phone_one")
    @NotNull(message = "*Please provide a phone number")
    // Voir la documentation ici: https://www.baeldung.com/java-regex-validate-phone-numbers
    @Pattern(regexp="^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                  + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                  + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",
            message="Please provide a valid phone number")
    @NotEmpty(message = "*Please provide city")
    private String linePhoneOne;

    @Column(name = "line_phone_two")
    @Pattern(regexp="^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$",
            message="Please provide a valid phone number")
    private String linePhoneTwo;

    @Column(name = "date_address_from")
    private Date dateAddressFrom;

    @Column(name = "date_address_to")
    private Date dateAddressTo;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey, name = "speaker_id", nullable=false, referencedColumnName = "speaker_id")
    private Speaker speaker;
}
