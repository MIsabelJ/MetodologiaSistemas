package com.example.buensaborback;

import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.entities.enums.Estado;
import com.example.buensaborback.domain.entities.enums.FormaPago;
import com.example.buensaborback.domain.entities.enums.TipoEnvio;
import com.example.buensaborback.domain.entities.enums.TipoPromocion;
import com.example.buensaborback.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class BuenSaborBackApplication {
// Aca tiene que inyectar todos los repositorios
// Es por ello que deben crear el paquete reositorio

// Ejemplo  @Autowired
//	private ClienteRepository clienteRepository;

	private static final Logger logger = LoggerFactory.getLogger(BuenSaborBackApplication.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ProvinciaRepository provinciaRepository;

	@Autowired
	private LocalidadRepository localidadRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private SucursalRepository	sucursalRepository;

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ArticuloInsumoRepository articuloInsumoRepository;

	@Autowired
	private ArticuloManufacturadoRepository articuloManufacturadoRepository;

	@Autowired
	private PromocionRepository promocionRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private  PedidoRepository pedidoRepository;


	public static void main(String[] args) {
		SpringApplication.run(BuenSaborBackApplication.class, args);
		logger.info("Me ejecutaste");
	}


	@Bean
	CommandLineRunner init(DetallePedidoRepository detallePedidoRepository) {
		return args -> {
			logger.info("----------------ESTOY----FUNCIONANDO---------------------");
			// Etapa del dashboard
			// Crear 1 pais
			// Crear 2 provincias para ese pais
			// crear 2 localidades para cada provincia
			Pais pais1 = Pais.builder().nombre("Argentina").build();
			paisRepository.save(pais1);

			logger.info("Pais {}", pais1);
			Provincia provincia1 = Provincia.builder().nombre("Mendoza").pais(pais1).build();
			Provincia provincia2 = Provincia.builder().nombre("Cordoba").pais(pais1).build();
			provinciaRepository.save(provincia1);

			logger.info("Provincia {}", provincia1);
			provinciaRepository.save(provincia2);
			logger.info("Provincia {}", provincia2);

			Localidad localidad1 = Localidad.builder().nombre("Lujan de Cuyo").provincia(provincia1).build();
			Localidad localidad2 = Localidad.builder().nombre("Godoy Cruz").provincia(provincia1).build();
			Localidad localidad3 = Localidad.builder().nombre("Achiras").provincia(provincia2).build();
			Localidad localidad4 = Localidad.builder().nombre("Agua de Oro").provincia(provincia2).build();
			localidadRepository.save(localidad1);
			logger.info("Localidad {}", localidad1);
			localidadRepository.save(localidad2);
			logger.info("Localidad {}", localidad2);
			localidadRepository.save(localidad3);
			logger.info("Localidad {}", localidad3);
			localidadRepository.save(localidad4);
			logger.info("Localidad {}", localidad4);

			// Crear 1 empresa
			Empresa empresaBrown = Empresa.builder().
					nombre("Lo de Brown").
					cuil(30503167).
					razonSocial("Venta de Alimentos").
					build();

			empresaRepository.save(empresaBrown);
			logger.info("Empresa {}", empresaBrown);


			// Crear 2 sucursales para esa empresa
			Sucursal sucursalChacras = Sucursal.builder().
					nombre("En chacras").
					empresa(empresaBrown).
					horarioApertura(LocalTime.of(17, 0)).
					horarioCierre(LocalTime.of(23, 0)).
					build();

			Domicilio domicilioViamonte = Domicilio.builder().
					cp(5509).calle("Viamonte").
					numero(500).
					localidad(localidad1).
					build();
			// Asigno domicilio
			sucursalChacras.setDomicilio(domicilioViamonte);

			logger.info("Sucursal {}", sucursalChacras);

			Sucursal sucursalGodoyCruz = Sucursal.builder().nombre("En godoy cruz").empresa(empresaBrown).horarioApertura(LocalTime.of(16, 0)).horarioCierre(LocalTime.of(23, 30)).esCasaMatriz(true).build();
			Domicilio domicilioSanMartin = Domicilio.builder().cp(5511).calle("San Martin").numero(789).localidad(localidad2).build();
			sucursalGodoyCruz.setDomicilio(domicilioSanMartin);
			logger.info("Sucursal {}", sucursalGodoyCruz);

			Sucursal sucursalGuaymallen = Sucursal.builder().
					nombre("En guaymallen").empresa(empresaBrown).horarioApertura(LocalTime.of(16, 0)).horarioCierre(LocalTime.of(23, 30)).build();
			Domicilio domicilioBerutti = Domicilio.builder().cp(5519).calle("Berutti").numero(2686).localidad(localidad2).build();
			sucursalGuaymallen.setDomicilio(domicilioBerutti);
			logger.info("Sucursal {}", sucursalGuaymallen);
			sucursalRepository.save(sucursalGuaymallen);


			Categoria categoriaPizzas = Categoria.builder().
					denominacion("Pizzas").
					sucursales(Set.of(sucursalChacras, sucursalGodoyCruz))
					.build();

			logger.info("Categoría {}", categoriaPizzas);

// Acá llevamos un stock por sucursal
			Categoria categoriaInsumos = Categoria.builder().
					denominacion("Insumos").
					sucursales(Set.of(sucursalChacras, sucursalGodoyCruz)).
					build();


			logger.info("Categoría {}", categoriaInsumos);

			Categoria categoriaBebidas = Categoria.builder().
					denominacion("Bebidas").
					sucursales(Set.of(sucursalChacras, sucursalGodoyCruz)).
					build();
   // subcategoria
			Categoria categoriaTragos = Categoria.builder().
					denominacion("Tragos").
					sucursales(Set.of(sucursalChacras, sucursalGodoyCruz)).
					build();

			categoriaBebidas.getSubCategorias().add(categoriaTragos);

			Categoria categoriaGaseosas = Categoria.builder().
					denominacion("Gaseosas").
					sucursales(Set.of(sucursalChacras, sucursalGodoyCruz)).
					build();

			categoriaBebidas.getSubCategorias().add(categoriaGaseosas);
			categoriaBebidas.getSubCategorias().add(categoriaTragos);

			logger.info("Categoría {}", categoriaBebidas);
			sucursalChacras.setCategorias(Set.of(categoriaBebidas, categoriaGaseosas, categoriaTragos, categoriaPizzas, categoriaInsumos));

			sucursalGodoyCruz.setCategorias(Set.of(categoriaBebidas, categoriaGaseosas, categoriaTragos, categoriaPizzas, categoriaInsumos));

// Aca Grabo todas las sucursales
			sucursalRepository.saveAll(Set.of(sucursalChacras, sucursalGodoyCruz));

			// Crear Unidades de medida
			UnidadMedida unidadMedidaLitros = UnidadMedida.builder().
					denominacion("Litros").
					build();
			UnidadMedida unidadMedidaGramos = UnidadMedida.builder().
					denominacion("Gramos").build();
			UnidadMedida unidadMedidaCantidad = UnidadMedida.builder().
					denominacion("Cantidad").build();
			UnidadMedida unidadMedidaPorciones = UnidadMedida.builder().
					denominacion("Porciones").build();
			unidadMedidaRepository.save(unidadMedidaLitros);

			logger.info("UnidadMedida {}", unidadMedidaLitros);
			unidadMedidaRepository.save(unidadMedidaGramos);
			logger.info("UnidadMedida {}", unidadMedidaGramos);
			unidadMedidaRepository.save(unidadMedidaCantidad);
			logger.info("UnidadMedida {}", unidadMedidaCantidad);
			unidadMedidaRepository.save(unidadMedidaPorciones);
			logger.info("UnidadMedida {}", unidadMedidaPorciones);


			//Crear Insumos , coca cola , harina , etc
			ArticuloInsumo cocaCola = ArticuloInsumo.builder().
					denominacion("Coca cola").
					unidadMedida(unidadMedidaLitros).
					esParaElaborar(false).
					categoria(categoriaGaseosas).
					stockActual(5).
					stockMaximo(50).
					precioCompra(50.0).
					precioVenta(70.0).
					build();
			ImagenArticuloInsumo imagenCoca = ImagenArticuloInsumo.builder().url("https://m.media-amazon.com/images/I/51v8nyxSOYL._SL1500_.jpg").build();

			cocaCola.getImagenes().add(imagenCoca);
			articuloInsumoRepository.save(cocaCola);
			logger.info("Insumo {}", cocaCola);

			ArticuloInsumo harina = ArticuloInsumo.builder().denominacion("Harina").unidadMedida(unidadMedidaGramos).esParaElaborar(true).categoria(categoriaInsumos).stockActual(4).stockMaximo(40).precioCompra(40.0).precioVenta(60.5).build();
			ImagenArticuloInsumo imagenHarina = ImagenArticuloInsumo.builder().url("https://mandolina.co/wp-content/uploads/2023/03/648366622-1024x683.jpg").build();
			harina.getImagenes().add(imagenHarina);
			articuloInsumoRepository.save(harina);
			logger.info("Insumo {}", harina);

			ArticuloInsumo tomate = ArticuloInsumo.builder().denominacion("Tomate").unidadMedida(unidadMedidaCantidad).esParaElaborar(true).categoria(categoriaInsumos).stockActual(20).stockMaximo(50).precioCompra(23.6).precioVenta(66.6).build();
			ImagenArticuloInsumo imagenTomate = ImagenArticuloInsumo.builder().url("https://thefoodtech.com/wp-content/uploads/2020/06/Componentes-de-calidad-en-el-tomate-828x548.jpg").build();
			tomate.getImagenes().add(imagenTomate);
			articuloInsumoRepository.save(tomate);
			logger.info("Insumo {}", tomate);

			ArticuloInsumo queso = ArticuloInsumo.builder().denominacion("Queso").unidadMedida(unidadMedidaGramos).esParaElaborar(true).categoria(categoriaInsumos).stockActual(20).stockMaximo(50).precioCompra(23.6).precioVenta(66.6).build();
			ImagenArticuloInsumo imagenQueso = ImagenArticuloInsumo.builder().url("https://superdepaso.com.ar/wp-content/uploads/2021/06/SANTAROSA-PATEGRAS-04.jpg").build();
			queso.getImagenes().add(imagenQueso);
			articuloInsumoRepository.save(queso);
			logger.info("Insumo {}", queso);

			// Crear Articulos Manufacturados
			ArticuloManufacturado pizzaMuzarella = ArticuloManufacturado.builder().
					denominacion("Pizza Muzarella").
					descripcion("Una pizza clasica").
					unidadMedida(unidadMedidaPorciones).
					precioVenta(130.0).
					tiempoEstimadoMinutos(15).
					preparacion("Esto se prepara asi").
					categoria(categoriaPizzas).
					build();

			ImagenArticuloManufacturado imagenPizzaMuzarella = ImagenArticuloManufacturado.builder().
					url("https://storage.googleapis.com/fitia-api-bucket/media/images/recipe_images/1002846.jpg")
					.build();

			pizzaMuzarella.getImagenes().add(imagenPizzaMuzarella);


			ArticuloManufacturadoDetalle detalleHarinaPizzaMuzarella = ArticuloManufacturadoDetalle.builder().
					cantidad(10d).
					articuloInsumo(harina).
					articuloManufacturado(pizzaMuzarella).
					build();

			ArticuloManufacturadoDetalle detalleQuesoPizzaMuzarella = ArticuloManufacturadoDetalle.builder().
					cantidad(20d).
					articuloInsumo(queso).
					articuloManufacturado(pizzaMuzarella).
					build();

			pizzaMuzarella.getArticuloManufacturadoDetalles().add(detalleHarinaPizzaMuzarella);
			pizzaMuzarella.getArticuloManufacturadoDetalles().add(detalleQuesoPizzaMuzarella);

			articuloManufacturadoRepository.save(pizzaMuzarella);
			logger.info("Manufacturado {}", pizzaMuzarella);

			ArticuloManufacturado pizzaNapolitana = ArticuloManufacturado.builder().denominacion("Pizza Napolitana").descripcion("Una pizza clasica con tomate").unidadMedida(unidadMedidaPorciones).precioVenta(150.0).tiempoEstimadoMinutos(15).preparacion("Esto se prepara asi").categoria(categoriaPizzas).build();
			ImagenArticuloManufacturado imagenPizzaNapolitana = ImagenArticuloManufacturado.builder().url("https://assets.elgourmet.com/wp-content/uploads/2023/03/8metlvp345_portada-pizza-1024x686.jpg.webp").build();
			pizzaNapolitana.getImagenes().add(imagenPizzaNapolitana);
			ArticuloManufacturadoDetalle detalleHarinaPizzaNapolatina = ArticuloManufacturadoDetalle.builder().cantidad(30d).articuloInsumo(harina).articuloManufacturado(pizzaNapolitana).build();
			ArticuloManufacturadoDetalle detalleQuesoPizzaNapolatina = ArticuloManufacturadoDetalle.builder().cantidad(10d).articuloInsumo(queso).articuloManufacturado(pizzaNapolitana).build();
			ArticuloManufacturadoDetalle detalleTomatePizzaNapolatina = ArticuloManufacturadoDetalle.builder().cantidad(20d).articuloInsumo(tomate).articuloManufacturado(pizzaNapolitana).build();
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalleHarinaPizzaNapolatina);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalleQuesoPizzaNapolatina);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalleTomatePizzaNapolatina);
			articuloManufacturadoRepository.save(pizzaNapolitana);
			logger.info("Manufacturado {}", pizzaNapolitana);

			// Crear promocion para sucursal - Dia de los enamorados
			// Tener en cuenta que esa promocion es exclusivamente para una sucursal determinada d euna empresa determinada
			Promocion promocionDiaEnamorados = Promocion.builder().
					denominacion("Dia de los Enamorados")
					.fechaDesde(LocalDate.of(2024, 2, 13))
					.fechaHasta(LocalDate.of(2024, 2, 15))
					.horaDesde(LocalTime.of(0, 0))
					.horaHasta(LocalTime.of(23, 59))
					.descripcionDescuento("14 de febrero es el día de los enamorados")
					.precioPromocional(180d)
					.tipoPromocion(TipoPromocion.Promocion).
					sucursales(Set.of(sucursalChacras,sucursalGodoyCruz))
					.build();
// Qué elementos contiene la promoción
			promocionDiaEnamorados.getArticulosInsumo().add(cocaCola);
			promocionDiaEnamorados.getArticulosManufacturado().add(pizzaNapolitana);

			ImagenPromocion imagenPromocionEnamorados = ImagenPromocion.builder().url("https://www.bbva.com/wp-content/uploads/2021/02/san-valentin-14-febrero-corazon-amor-bbva-recurso-1920x1280-min.jpg").build();
			promocionDiaEnamorados.getImagenes().add(imagenPromocionEnamorados);

			promocionRepository.save(promocionDiaEnamorados);
            logger.info("Promocion {}", promocionDiaEnamorados);


			logger.info("Promocion {}", promocionDiaEnamorados);
		// Caso de estudio de la Promoción
// Para preguntar cuántas promociones tiene una sucursal determinada
			sucursalChacras.setPromociones(Set.of(promocionDiaEnamorados));

			sucursalGodoyCruz.setPromociones(Set.of(promocionDiaEnamorados));

// Actualiza el repo de sucursales con las promociones asignadas
			sucursalRepository.saveAll(Set.of(sucursalChacras, sucursalGodoyCruz));



			// agregar usuario
			Usuario usuario1 = Usuario.builder().username("metodolo2024").
					auth0Id("dfdfdffdd hjhhjhjhjjhjh").build();
			usuarioRepository.save(usuario1);
			logger.info("Usuario {}", usuario1);

			//Agregar cliente
			ImagenCliente imagenCliente = ImagenCliente.builder().url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsa2xSPPay4GD7E3cthBMCcvPMADEjFufUWQ&s").build();

			Domicilio domicilioCliente1 = Domicilio.builder().calle("Sarmiento").numero(123).cp(5507).localidad(localidad1).build();
			Domicilio domicilioCliente2 = Domicilio.builder().calle("San martin").numero(412).cp(5501).localidad(localidad2).build();

			Cliente cliente1 = Cliente.builder().
					nombre("alumno").
					email("aluno@gmail.com").
					apellido("tuApellido").
					imagen(imagenCliente).
					telefono("2222222").
					usuario(usuario1).
					fechaNacimiento(LocalDate.of(2000, 11, 12)).
					build();

	// Asignamos los domicilios

			cliente1.getDomicilios().add(domicilioCliente1);
			cliente1.getDomicilios().add(domicilioCliente2);

			clienteRepository.save(cliente1);
			logger.info("Cliente {}", cliente1);

			// agregar pedido

			Pedido pedido = Pedido.builder()
					.domicilio(domicilioCliente1)
					.estado(Estado.Entregado)
					.formaPago(FormaPago.MercadoPago)
					.fechaPedido(LocalDate.of(2024, 5, 12))
					.horaEstimadaFinalizacion(LocalTime.of(12, 30))
					.sucursal(sucursalChacras)
					.tipoEnvio(TipoEnvio.Delivery)
					.total(345d)
					.totalCosto(333d)
					.cliente(cliente1)
					.build();

			DetallePedido detallePedido1 = DetallePedido.builder().
					articuloManufacturado(pizzaMuzarella).
					cantidad(1).
					subTotal(130d).
					build();
			DetallePedido detallePedido2 = DetallePedido.builder().articuloInsumo(cocaCola).cantidad(1).subTotal(70d).build();

			pedido.getDetallePedidos().add(detallePedido1);
			pedido.getDetallePedidos().add(detallePedido2);


			pedidoRepository.save(pedido);

			Factura factura = Factura.builder().
					fechaFacturacion(LocalDate.of(2024, 2, 13)).
					formaPago(FormaPago.MercadoPago).
					mpMerchantOrderId(1).mpPaymentId(1).
					mpPaymentType("mercado pago").mpPreferenceId("0001").
					totalVenta(2500d).
					pedido(pedido).build();

			pedido.setFactura(factura);

	// -------- Plantear todos los casos de pruebas para Reventar el modelo -------
			//Pruebas del modelo
			//Editamos el nombre de la Empresa
			empresaBrown.setNombre("Los Ocho Hermanos");
			logger.info("Empresa{}", empresaBrown);
			empresaRepository.save(empresaBrown);

			//Borrado de una sucursal que tiene domicilio pero sin promocion ni categoria
			sucursalRepository.delete(sucursalGuaymallen);

			//borrado de un detalle pedido de Pedido
			pedido.getDetallePedidos().remove(detallePedido1);
			logger.info("Pedido {}", pedido);

			logger.info("DetallePedido {}", detallePedido1);
			pedidoRepository.save(pedido);

			detallePedidoRepository.delete(detallePedido1);




		};
	}
}







