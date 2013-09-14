package pe.joedayz.viajero.domain;

import java.util.Date;

public class Gasto {
	
	private Integer id;
	private Date fecha;
	private String categoria;
	private String descripcion;
	private Double valor;
	private String local;
	private Integer viajeId;
	
	public Gasto(){}
	
	public Gasto(Integer id, Date fecha, String categoria, String descricao,
			Double valor, String local, Integer viajeId) {
		this.id = id;
		this.fecha = fecha;
		this.categoria = categoria;
		this.descripcion = descricao;
		this.valor = valor;
		this.local = local;
		this.viajeId = viajeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}

	public Integer getViajeId() {
		return viajeId;
	}

	public void setViajeId(Integer viajeId) {
		this.viajeId = viajeId;
	}

	
}
