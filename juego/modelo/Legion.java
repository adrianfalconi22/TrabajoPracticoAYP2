package modelo;

import java.util.LinkedList;
import java.util.List;

public class Legion extends Unidad {

	protected List<Unidad> legion = new LinkedList<>();
	private String nombreLegion;
	private int auxiliares;
	private int legionarios;
	private int centuriones;

	public Legion(String nombre) {

		this.nombreLegion = nombre;

	}

	public Legion() {

	}

	@Override
	public double getVida() {

		double vidaTotal = 0;
		for (Unidad unidad : legion) {
			if (unidad.estaVivo()) {
				vidaTotal += unidad.getVida();

			}
		}

		return vidaTotal;
	}

	public List<Unidad> getLegion() {

		return legion;
	}

	@Override
	public double getDaņo() {
		double daņo = 0;
		double daņoDeCenturiones = 0;
		for (Unidad unidad : legion) {

			if (unidad.tipo == TipoUnidad.AUXILIAR
					|| unidad.tipo == TipoUnidad.LEGIONARIO) {

				daņo += unidad.getDaņo();
			} else if (unidad.tipo == TipoUnidad.CENTURION) {
				daņoDeCenturiones += (unidad.getDaņo() + ((double) getCantidadDeCenturiones() / 10));
			}
		}

		return daņo + daņoDeCenturiones;
	}

	@Override
	public double getCosto() {

		double costoTotal = 0;
		for (Unidad unidad : legion) {

			costoTotal += unidad.getCosto();
		}

		return costoTotal;
	}

	public int getCantidadDeCenturiones() {
		int total = 0;

		for (Unidad unidad : legion) {

			if (unidad.tipo == TipoUnidad.CENTURION) {
				total++;
			}
		}

		return total;
	}

	public void aņadirUnidad(Unidad unidad) {
		legion.add(unidad);

	}

	public void atacarLegion(Legion otraLegion) {
		double vida = 0;
		double controladorDeDaņo = getDaņo();

		for (Unidad unidad : otraLegion.getLegion()) {

			if (unidad.estaVivo() && unidad.tipo == TipoUnidad.AUXILIAR
					&& controladorDeDaņo > 0) {

				if (controladorDeDaņo >= 100) {
					vida = unidad.getVida();

					unidad.setVida(vida);
					controladorDeDaņo = controladorDeDaņo - vida;
				} else if (controladorDeDaņo < 100) {
					unidad.setVida(controladorDeDaņo);
					controladorDeDaņo = controladorDeDaņo - getDaņo();

				}

			} else if (unidad.estaVivo() && !hayAuxiliares()
					&& unidad.tipo == TipoUnidad.LEGIONARIO
					&& controladorDeDaņo > 0) {
				if (controladorDeDaņo >= 100) {
					vida = unidad.getVida();

					unidad.setVida(vida);
					controladorDeDaņo = controladorDeDaņo - vida;
				} else if (controladorDeDaņo < 100) {
					unidad.setVida(controladorDeDaņo);
					controladorDeDaņo = controladorDeDaņo - getDaņo();

				}

			} else if (unidad.estaVivo()
					&& (!hayAuxiliares() && !hayLegionarios())
					&& unidad.tipo == TipoUnidad.CENTURION
					&& controladorDeDaņo > 0) {
				if (controladorDeDaņo >= 100) {
					vida = unidad.getVida();

					unidad.setVida(vida);
					controladorDeDaņo = controladorDeDaņo - vida;
				} else if (controladorDeDaņo < 100) {
					unidad.setVida(controladorDeDaņo);
					controladorDeDaņo = controladorDeDaņo - getDaņo();

				}
			}
		}
		//
	}

	public String toString(String separador) {
		return nombreLegion + separador + auxiliares + separador + legionarios + separador
				+ centuriones;
	}

	public void removerSoldadosMuertos() {

		for (Unidad unidad : legion) {

			if (!unidad.estaVivo()) {
				legion.remove(unidad);
			}
		}

	}

	private boolean hayAuxiliares() {

		int totalDeAuxiliares = 0;

		for (Unidad unidad : legion) {
			if (unidad.tipo == TipoUnidad.AUXILIAR) {
				totalDeAuxiliares++;
			}
		}

		return totalDeAuxiliares > 0;
	}

	public boolean hayLegionarios() {
		int totalDeLegionarios = 0;

		for (Unidad unidad : legion) {
			if (unidad.tipo == (TipoUnidad.LEGIONARIO)) {
				totalDeLegionarios++;
			}
		}

		return totalDeLegionarios > 0;
	}

	public void imprimirListaDeLaUnidades() {
		for (Unidad unidad : legion) {
			System.out.println(unidad.getVida());
		}
	}

	public int contarUnidades() {

		int unidades = 0;

		for (Unidad unidad : legion) {
			if (unidad.estaVivo()) {
				unidades++;
			}
		}

		return unidades;
	}

	public void aumentarAuxiliares() {
		auxiliares++;

	}

	public void aumentarLegionarios() {
		legionarios++;

	}

	public void aumentarCenturiones() {
		centuriones++;

	}

	public String getNombre() {
		return nombreLegion;
	}
}
