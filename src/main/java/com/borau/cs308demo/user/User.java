package com.borau.cs308demo.user;

import com.borau.cs308demo.cart.Cart;
import com.borau.cs308demo.order.Order;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.borau.cs308demo.address.Address;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private String id;

    @Indexed
    private String email;

    private String password;
    private String name;
    private String surname;
    private String phone;
    private String age;
    private String taxId;

    //ileride max address sayısı belirlenmesi lazım, @Max ile yapılır belki
    private List<Address> addresses;

    private Set<String> roles;
    private String registerIp;
    private String registerDate;
    private Cart cart;
    private List<Order> orders;
    private Boolean isActive = true;
    private List<String> wishlist;



    // müşteri ya direkt cc/havale ile ödeme yapacak and/or bakiye yükleme şeklinde yapacak, dizayn tarzına bağlı olarak
    // bu field'i kullanabiliriz. Daha kaliteli bir ödeme sistemi entegre edilebilir tabi, yada "Yemeksepeti Cüzdan" gibi
    // günümüz fintechvari firmalarının uygulama içi cüzdan yöntemini kullanabiliriz, Alışveriş sepetini bu çüzdan yöntemini
    // seçerek ödemek isteyen birine özel indirim uygulanır vs vs.
    //private Double balance;

    public User(String email, String password, String name, String surname, String phone, String age, String taxId, List<Address> addresses, Set<String> roles, String registerIp, String registerDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.age = age;
        this.taxId = taxId;
        this.addresses = addresses;
        this.roles = roles;
        this.registerIp = registerIp;
        this.registerDate = registerDate;
    }

    public User(String name, String password, String email, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}