package jpdr.apps.devsu.apidemo.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReporteDTO {
	
	@JsonProperty("Título del Reporte")
	private String tituloReporte;
	
	@JsonProperty("Cliente ID")
	private int clienteId;
	
	@JsonProperty("Nombres")
	private String nombreCliente;
	
	@JsonProperty("Fecha Desde")
	private String fechaDesde;

	@JsonProperty("Fecha Hasta")
	private String fechaHasta;
	
	@JsonProperty("Cantidad de Movimientos")
	private int cantidadMovimientos;
	
	@JsonProperty("Detalle de Movimientos")
	private List<ReporteDTOItem> detalleMovimientos;
	
	public ReporteDTO () {}

	public ReporteDTO(String tituloReporte, int clienteId, String nombreCliente, String fechaDesde, String fechaHasta,
			int cantidadMovimientos, List<ReporteDTOItem> detalleMovimientos) {
		super();
		this.tituloReporte = tituloReporte;
		this.clienteId = clienteId;
		this.nombreCliente = nombreCliente;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.cantidadMovimientos = cantidadMovimientos;
		this.detalleMovimientos = detalleMovimientos;
	}

	public String getTituloReporte() {
		return tituloReporte;
	}

	public void setTituloReporte(String tituloReporte) {
		this.tituloReporte = tituloReporte;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public int getCantidadMovimientos() {
		return cantidadMovimientos;
	}

	public void setCantidadMovimientos(int cantidadMovimientos) {
		this.cantidadMovimientos = cantidadMovimientos;
	}

	public List<ReporteDTOItem> getDetalleMovimientos() {
		return detalleMovimientos;
	}

	public void setDetalleMovimientos(List<ReporteDTOItem> detalleMovimientos) {
		this.detalleMovimientos = detalleMovimientos;
	}

	@Override
	public String toString() {
		return "ReporteDTO [tituloReporte=" + tituloReporte + ", clienteId=" + clienteId + ", nombreCliente="
				+ nombreCliente + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", cantidadMovimientos="
				+ cantidadMovimientos + ", detalleMovimientos=" + detalleMovimientos + "]";
	}

	
	

}
