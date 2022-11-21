package com.pedidosloja.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ForeignKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;

	private Date data_pedido;

	@JsonIgnore
	@ForeignKey(name = "cliente_id")
	@ManyToOne
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<ItensDoPedido> itensDoPedido = new ArrayList<ItensDoPedido>();

	private double valor_frete;

	private double valor_total_pedido;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Date getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItensDoPedido> getItensDoPedido() {
		return itensDoPedido;
	}

	public void setItensDoPedido(List<ItensDoPedido> itensDoPedido) {
		this.itensDoPedido = itensDoPedido;
	}

	public double getValor_frete() {
		return valor_frete;
	}

	public void setValor_frete(double valor_frete) {
		this.valor_frete = valor_frete;
	}

	public double getValor_total_pedido() {
		return valor_total_pedido;
	}

	public void setValor_total_pedido(double valor_total_pedido) {
		this.valor_total_pedido = valor_total_pedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return codigo == other.codigo;
	}

}
