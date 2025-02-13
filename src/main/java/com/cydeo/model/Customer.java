package com.cydeo.model;

import com.cydeo.enums.MembershipType;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

    @NotEmpty(message = "{customer.firstName.notEmpty}")
    @Size(min = 3, max = 10, message = "{customer.firstName.size}")
    private String firstName;

    @NotEmpty(message = "{customer.lastName.notNull}") // Changed from @NotNull
    @Size(min = 2, max = 10, message = "{customer.lastName.size}")
    private String lastName;

    @NotEmpty(message = "{customer.state.notEmpty}")
    private String state;

    @NotEmpty(message = "{customer.zipCode.notEmpty}")
    @Pattern(regexp = "\\d{5}(-\\d{4})?", message = "{customer.zipCode.invalid}")
    private String zipcode;

    @NotEmpty(message = "{customer.city.notEmpty}")
    private String city;

    @NotEmpty(message = "{customer.email.notEmpty}")
    @Email(message = "{customer.email.invalid}")
    private String email;

    @NotEmpty(message = "{customer.address1.notEmpty}")
    private String address1;

    private String address2;

    @NotNull(message = "{customer.membershipType.notNull}")
    private MembershipType membershipType;

    @AssertTrue(message = "{customer.agreeTerms.required}")
    private boolean agreeTerms;
}