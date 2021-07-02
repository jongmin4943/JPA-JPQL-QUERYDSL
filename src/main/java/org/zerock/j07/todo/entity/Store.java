package org.zerock.j07.todo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_store")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private String menu;

    @Column(nullable = false)
    private String address;

    public void changeMenu(String menu) {
        this.menu = menu;
    }
}
