package ru.iskhakov.walletsapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet", schema = "public")
public class Wallet {

    @Id
    @Builder.Default
    private final String id = UUID.randomUUID().toString();
    @Column
    private BigDecimal balance;
}