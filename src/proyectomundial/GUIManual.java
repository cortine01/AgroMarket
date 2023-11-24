package proyectomundial;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import proyectomundial.util.ConexionOracle;

public class GUIManual extends JFrame {
    
    ConexionOracle conn = new ConexionOracle();
    
    
    // Matrix que permite almancenar la información de las selecciones futbol cargadas
    public String[][] selecciones = null;
    
    // Matriz que permite almacenar los resultados de los partidos cargardos
    public String[][] resultados = null;
    public String[][] resultadosRespaldo = null;
    public boolean guardadoRespaldo = false;
    
    // Elementos de bara Lateral
    private JPanel jPanelLeft;
    
    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;
    
    // Elementos de opciones de Menú
    private JPanel jPanelMenu;
    
    private JPanel jPanelMenuHome;
    private JLabel btnHome;
    
    private JPanel jPanelMenuPublicaciones;
    private JLabel btnSelecciones;
    
    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;
    
    private JPanel jPanelMenuNotificaciones;
    private JLabel btnNotificaciones;
    
    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;
    
    private JPanel jPanelMenuAuditoria;
    private JLabel btnAuditoria;
        
    // Elementos de panel de contenido
    private JPanel jPanelRight;
    
    private JPanel jPanelMain;
    
    
    public GUIManual() {
        
        // Se inician los componentes gráficos
        initComponents();
        
        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionPublicaciones();
        
    }
    
    
    private void initComponents() {
        
        
        conn.desconectar();

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();
        
        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();
        
        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();
        
        jPanelMenuPublicaciones = new JPanel();
        btnSelecciones = new JLabel();
        
        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();
        
        jPanelMenuNotificaciones = new JPanel();
        btnNotificaciones = new JLabel();
        
        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();
        
        jPanelMenuAuditoria = new JPanel();
        btnAuditoria = new JLabel();
        
        // Pinta el logo de la aplicación
        pintarLogo();
        
        // Pinta la opción de menú del Home
        pintarMenuHome();
        
        // Pinta la opción de Menú de las Selecciones
        pintarMenuPublicaciones();
        
        // Pinta la opción de Menú de los resultados
        pintarMenuMisPublicaciones();
        
        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuNotificaciones();
        
        // Pinta la opción de Menú del dahboard de resultados
        pintarMenuDashboardRes();
        
        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();
        
        
        
        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelMain = new JPanel();
        
        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();
        
        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();
        
        setTitle("Mundial");
        pack();
        setVisible(true);
    }
    
    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(200, 200)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/agro_market.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);
        
    }
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación del HOME
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar la página de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/user.png"))); // NOI18N
        btnHome.setText("Usuario");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(255, 0, 63));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);
        
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Home");
                accionHome();
            }
        });   
    }
    
    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de navegación Home
     * Permite modificar la etiqueta de Navegación en Home, remover los elementos que hay en 
     * el panel de contenidos y agregar la imagen de inicio de la aplicación
     */
    private void accionHome() {

        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();

        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/home.jpg"))); // NOI18N
        homePanel.add(imageHome);

        jPanelMain.add(homePanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación de SELECCIONES
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar las selecciones de futbol cargadas en la aplicación
     */
    private void pintarMenuPublicaciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/publis.png"))); // NOI18N
        btnSelecciones.setText("Publicaciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioSelecciones = new JLabel();
        jPanelMenuPublicaciones.setBackground(new java.awt.Color(2, 126, 7));
        jPanelMenuPublicaciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuPublicaciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuPublicaciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuPublicaciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuPublicaciones);
        
        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Publicaciones");
                accionPublicaciones();
            }
        });
    }
    
    private void accionPublicaciones() {
        //pintarTablaSelecciones();
        JTextArea a = new JTextArea();
        a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de resultados \n"
                + "se deben mostrar los siguientes datos:\n\n"
                + "1. Número de partidos cargados \n"
                + "2. Promedio de goles por partido \n"
                + "3. Partido con más goles y partido con menos goles \n"
                + "4. Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate \n"
                + "5. Selcción o selecciones con más goles y con menos goles \n"
                + "6. Selección con más puntos y menos puntos \n"
                + "7. Continente o continentes con más goles y menos goles \n"
                + "8. Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo) \n\n"
                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");
        
        //Crear el Panel que contiene todo y ponerle 3 columnas
        JPanel resultadosPanel = new JPanel();
        resultadosPanel.setLayout(new GridLayout(6, 3, 10, 5));
        //resultadosPanel.setPreferredSize((new java.awt.Dimension(600, 830)));
        //resultadosPanel.setMaximumSize(jPanelRight.getPreferredSize());
        
        JScrollPane scrollResultadosPanel = new JScrollPane(resultadosPanel);
        scrollResultadosPanel.setPreferredSize((new java.awt.Dimension(620, 540)));
        
        for (int i = 0; i < 16; i++) {
            //Creacion de puntos y ponerlas en layout box
            JPanel Producto = new JPanel();
            Producto.setLayout(new BoxLayout(Producto, BoxLayout.Y_AXIS));
            resultadosPanel.add(Producto);

            //Creacion Producto        
            JPanel ContenedorProducto = new JPanel();
            ContenedorProducto.setBackground(new java.awt.Color(1,50,3));
            ContenedorProducto.setLayout(new BorderLayout());

            JLabel ImgProducto = new JLabel();
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/ImagenProductos/arroz.jpeg"));
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajusta el tamaño de la imagen
            ImgProducto.setIcon(new ImageIcon(newImage));

            JPanel Pro_Nombre_precio = new JPanel();
            Pro_Nombre_precio.setLayout(new BoxLayout(Pro_Nombre_precio, BoxLayout.Y_AXIS));
            Pro_Nombre_precio.setBackground(new java.awt.Color(2,126,7));

            JLabel productoNombre = new JLabel();
            productoNombre.setText("<html><div style='text-align: left;'>Maiz</div></html>");
            productoNombre.setForeground(new java.awt.Color(249,249,250));
            productoNombre.setFont((new Font(productoNombre.getFont().getName(),Font.BOLD,26)));

            JLabel productoPrecio = new JLabel();
            productoPrecio.setText("<html><div style='text-align: right;'>30000</div></html>");
            productoPrecio.setForeground(new java.awt.Color(249,249,250));
            productoPrecio.setFont((new Font(productoNombre.getFont().getName(),Font.BOLD,20)));

            Pro_Nombre_precio.add(productoNombre);
            Pro_Nombre_precio.add(productoPrecio);

            ContenedorProducto.add(Pro_Nombre_precio, BorderLayout.SOUTH);
            ContenedorProducto.add(ImgProducto, BorderLayout.CENTER);
            Producto.add(ContenedorProducto);
        }
        
        jPanelMain.removeAll();
        //jPanelMain.add(a);
        
        jPanelMain.add(scrollResultadosPanel, BorderLayout.PAGE_START);
        
        jPanelMain.repaint();
        jPanelMain.revalidate();        
    }
    
    
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);
        
        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));
        
        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(1, 50, 3));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize( jPanelLeft.getPreferredSize());
    }
    
    
    public void pintarTablaSelecciones() {

        JTable table = conn.consulta("SELECT * FROM publicaciones_pbe");
        table.setRowHeight(30);
        
        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize( jPanelRight.getPreferredSize());
        
        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(scrollPane);
        
        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
        
    }
    
    
    private void pintarMenuMisPublicaciones() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/mis_publis.png"))); // NOI18N
        btnResultados.setText("Mis Publicaciones");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(2, 126, 7));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);
        
        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accionMisPublicaciones();
            }
        });
    }
    
    
    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de navegación Resultados
     * Permite ver la lista de resultados que se encuentran cargadas en la aplicación. 
     * Si la lista de resultados en vacía, muestra un botón que permite cargar un archivo CSV
     * con la información de los resultados
     */
    private void accionMisPublicaciones() {
        //pintarTablaResultados();
        JTextArea a = new JTextArea();
        a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de resultados \n"
                + "se deben mostrar los siguientes datos:\n\n"
                + "1. Número de partidos cargados \n"
                + "2. Promedio de goles por partido \n"
                + "3. Partido con más goles y partido con menos goles \n"
                + "4. Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate \n"
                + "5. Selcción o selecciones con más goles y con menos goles \n"
                + "6. Selección con más puntos y menos puntos \n"
                + "7. Continente o continentes con más goles y menos goles \n"
                + "8. Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo) \n\n"
                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");
        
        //Crear el Panel que contiene todo y ponerle 3 columnas
        JPanel resultadosPanel = new JPanel();
        resultadosPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        resultadosPanel.setPreferredSize(new Dimension(620, 540));
        
        JScrollPane scrollResultadosPanel = new JScrollPane(resultadosPanel);
        scrollResultadosPanel.setPreferredSize((new java.awt.Dimension(620, 540)));
        
        for (int i = 0; i < 3; i++) {
            //Creacion de puntos y ponerlas en layout box
            JPanel Producto = new JPanel();
            Producto.setLayout(new BorderLayout());
            Producto.setPreferredSize(new Dimension(200, 200)); // Tamaño fijo

            //Creacion Producto        
            JPanel ContenedorProducto = new JPanel();
            ContenedorProducto.setBackground(new java.awt.Color(1,50,3));
            ContenedorProducto.setLayout(new BorderLayout());

            JLabel ImgProducto = new JLabel();
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/ImagenProductos/arroz.jpeg"));
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajusta el tamaño de la imagen
            ImgProducto.setIcon(new ImageIcon(newImage));

            JPanel Pro_Nombre_precio = new JPanel();
            Pro_Nombre_precio.setLayout(new BoxLayout(Pro_Nombre_precio, BoxLayout.Y_AXIS));
            Pro_Nombre_precio.setBackground(new java.awt.Color(2,126,7));

            JLabel productoNombre = new JLabel();
            productoNombre.setText("<html><div style='text-align: left;'>Maiz</div></html>");
            productoNombre.setForeground(new java.awt.Color(249,249,250));
            productoNombre.setFont((new Font(productoNombre.getFont().getName(),Font.BOLD,26)));

            JLabel productoPrecio = new JLabel();
            productoPrecio.setText("<html><div style='text-align: right;'>30000</div></html>");
            productoPrecio.setForeground(new java.awt.Color(249,249,250));
            productoPrecio.setFont((new Font(productoNombre.getFont().getName(),Font.BOLD,20)));

            Pro_Nombre_precio.add(productoNombre);
            Pro_Nombre_precio.add(productoPrecio);

            ContenedorProducto.add(Pro_Nombre_precio, BorderLayout.SOUTH);
            ContenedorProducto.add(ImgProducto, BorderLayout.CENTER);
            Producto.add(ContenedorProducto);
            
            // Agregar un espacio entre los productos
            ContenedorProducto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            Producto.add(ContenedorProducto);
            resultadosPanel.add(Producto);
        }
        
        jPanelMain.removeAll();
        //jPanelMain.add(a);
        
        jPanelMain.add(scrollResultadosPanel, BorderLayout.PAGE_START);
        
        jPanelMain.repaint();
        jPanelMain.revalidate();

    }
    
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación de Dashboard de Selecciones
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar los diferentes datos que será extraidos de la información de 
     * las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuNotificaciones() {
        btnNotificaciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/notis.png")));
        btnNotificaciones.setText("Notificaciones");
        btnNotificaciones.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioNotificacionesecciones = new JLabel();
        jPanelMenuNotificaciones.setBackground(new java.awt.Color(2, 126, 7));
        jPanelMenuNotificaciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuNotificaciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuNotificaciones.add(vacioNotificacionesecciones, BorderLayout.WEST);
        jPanelMenuNotificaciones.add(btnNotificaciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuNotificaciones);
        
        btnNotificaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Selecciones");
                accionNotificaciones();
            }
        });
    }
    
    
    /**
     * TRABAJO DEL ESTUDIANTE
     * Se debe módificar este método para poder calcular y pintar las diferentes informaciones que son solicitadas
     * Revise el proceso que se siguen en los demás métodos para poder actualizar la información de los paneles
     */
        private void accionNotificaciones() {
        
        
        JTextArea a = new JTextArea();
        a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de selecciones \n"
                + "se deben mostrar los siguientes datos:\n\n"
                + "1. Total de selecciones Cargadas \n"
                + "2. Número de selecciones por continente (Se puede usar una tabla para pintar esto) \n"
                + "3. Cantidad de nacionalidades diferentes de los directores técnicos \n"
                + "4. Ranking de nacionalidades de directores técnicos \n\n"
                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");
        
        //JPanel form = new JPanel();
        //form.setLayout(new GridLayout(2, 5, 3, 5));
        
        JPanel seleccionesPanel = new JPanel();
        
        seleccionesPanel.setLayout(new GridLayout(2, 2, 5, 5));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize( jPanelRight.getPreferredSize());
        
        JPanel Punto1 = new JPanel();
        Punto1.setLayout(new BoxLayout(Punto1, BoxLayout.Y_AXIS));
        
        JPanel Punto2 = new JPanel();
        Punto2.setLayout(new BoxLayout(Punto2, BoxLayout.Y_AXIS));
        
        JPanel Punto3 = new JPanel();
        Punto3.setLayout(new BoxLayout(Punto3, BoxLayout.Y_AXIS));
        
        JPanel Punto4 = new JPanel();
        Punto4.setLayout(new BoxLayout(Punto4, BoxLayout.Y_AXIS));
        
        //Punto 1
        JPanel ContenedorPunto1 = new JPanel();
        ContenedorPunto1.setBackground(new java.awt.Color(0,24,47));
        ContenedorPunto1.setLayout(new FlowLayout((int)LEFT_ALIGNMENT));
        
        JLabel label = new JLabel();
        label.setText(" Número de selecciones");
        label.setForeground(new java.awt.Color(249,249,250));
        label.setFont((new Font(label.getFont().getName(),Font.BOLD,26)));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel label_2 = new JLabel();
        label_2.setText(" cargadas:");
        label_2.setForeground(new java.awt.Color(249,249,250));
        label_2.setFont((new Font(label_2.getFont().getName(),Font.BOLD,26)));
        label_2.setVerticalAlignment(JLabel.TOP);
        label_2.setHorizontalAlignment(JLabel.LEFT);
        
        JPanel ContenedorRespuesta1 = new JPanel();
        ContenedorRespuesta1.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta1.setPreferredSize((new java.awt.Dimension(297, 114)));
        ContenedorRespuesta1.setLayout(new BorderLayout(0, 0));
        
        JLabel Respuesta1 = new JLabel();
        Respuesta1.setText("12"); //El Texto Va aqui
        Respuesta1.setForeground(new java.awt.Color(0,24,47));
        Respuesta1.setFont((new Font(label.getFont().getName(),Font.BOLD,80)));
        Respuesta1.setVerticalAlignment(JLabel.CENTER);
        Respuesta1.setHorizontalAlignment(JLabel.CENTER);
        ContenedorRespuesta1.add(Respuesta1);
        
        ContenedorPunto1.add(label);
        ContenedorPunto1.add(label_2);
        ContenedorPunto1.add(ContenedorRespuesta1);
        Punto1.add(ContenedorPunto1);
        
        //punto2
        /*
        String[][] Respuesta2Relleno = null;      
        
        Punto2.add(
        crearGraficaPie(Respuesta2Relleno,
        "Cantidad de selecciones por continente",
        "Selecciones",
        "Cantidad",
        PlotOrientation.VERTICAL,
        false,
        false,
        false,
        new java.awt.Color(9,72,132)
        ));
        */
        
        //Punto3        
        JPanel ContenedorPunto2 = new JPanel();
        ContenedorPunto2.setBackground(new java.awt.Color(9,72,132));
        ContenedorPunto2.setLayout(new FlowLayout((int)LEFT_ALIGNMENT));
        
        JLabel label3 = new JLabel();
        label3.setText(" Numero de nacionalidades");
        label3.setForeground(new java.awt.Color(249,249,250));
        label3.setFont((new Font(label3.getFont().getName(),Font.BOLD,18)));
        label3.setVerticalAlignment(JLabel.TOP);
        label3.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel label3_2 = new JLabel();
        label3_2.setText(" diferentes entre los");
        label3_2.setForeground(new java.awt.Color(249,249,250));
        label3_2.setFont((new Font(label3_2.getFont().getName(),Font.BOLD,18)));
        label3_2.setVerticalAlignment(JLabel.TOP);
        label3_2.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel label3_3 = new JLabel();
        label3_3.setText(" directores tecnicos:");
        label3_3.setForeground(new java.awt.Color(249,249,250));
        label3_3.setFont((new Font(label3_3.getFont().getName(),Font.BOLD,18)));
        label3_3.setVerticalAlignment(JLabel.TOP);
        label3_3.setHorizontalAlignment(JLabel.LEFT);
        
        JPanel ContenedorRespuesta2 = new JPanel();
        ContenedorRespuesta2.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta2.setPreferredSize((new java.awt.Dimension(297, 105)));
        ContenedorRespuesta2.setLayout(new BorderLayout(0, 0));
        
        JLabel Respuesta3 = new JLabel();
        Respuesta3.setText("23"); //Texto va aqui
        Respuesta3.setForeground(new java.awt.Color(0,24,47));
        Respuesta3.setFont((new Font(label3.getFont().getName(),Font.BOLD,80)));
        Respuesta3.setVerticalAlignment(JLabel.CENTER);
        Respuesta3.setHorizontalAlignment(JLabel.CENTER);
        ContenedorRespuesta2.add(Respuesta3);
        
        ContenedorPunto2.add(label3);
        ContenedorPunto2.add(label3_2);
        ContenedorPunto2.add(label3_3);
        ContenedorPunto2.add(ContenedorRespuesta2);
        Punto3.add(ContenedorPunto2);
        
        /*
        //Punton4        
        String[][] Respuesta4Relleno = null;      
        
        Punto4.add(
        crearGraficaBarras(Respuesta4Relleno,
        "Ranking de nacionalidades de directores técnicos",
        "Directores Tecnicos",
        "Cantidad",
        PlotOrientation.VERTICAL,
        false,
        false,
        false,
        new java.awt.Color(18,119,217)
        ));
        */
        
        //experimento.setText("a ");
        seleccionesPanel.add(Punto1);
        seleccionesPanel.add(Punto2);
        seleccionesPanel.add(Punto3);
        seleccionesPanel.add(Punto4);
        
        
        jPanelMain.removeAll();
        //jPanelMain.add(a);
        
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        
        jPanelMain.repaint();
        jPanelMain.revalidate();        
    }
    
        
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Mis Publicaciones");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(2, 126, 7));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);
        
        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Resultados");
                accionDashboardRes();
            }
        });
    }
    
    
    /**
     * TRABAJO DEL ESTUDIANTE
     * Se debe módificar este método para poder calcular y pintar las diferentes informaciones que son solicitadas
     * Revise el proceso que se siguen en los demás métodos para poder actualizar la información de los paneles
     */
    private void accionDashboardRes() {
        
        
        JTextArea a = new JTextArea();
        a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de resultados \n"
                + "se deben mostrar los siguientes datos:\n\n"
                + "1. Número de partidos cargados \n"
                + "2. Promedio de goles por partido \n"
                + "3. Partido con más goles y partido con menos goles \n"
                + "4. Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate \n"
                + "5. Selcción o selecciones con más goles y con menos goles \n"
                + "6. Selección con más puntos y menos puntos \n"
                + "7. Continente o continentes con más goles y menos goles \n"
                + "8. Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo) \n\n"
                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");
        
        //Crear el Panel que contiene todo y ponerle dos columnas
        JPanel resultadosPanel = new JPanel();
        resultadosPanel.setLayout(new GridLayout(4, 2, 5, 5));
        resultadosPanel.setPreferredSize((new java.awt.Dimension(600, 830)));
        resultadosPanel.setMaximumSize(jPanelRight.getPreferredSize());
        
        JScrollPane scrollResultadosPanel = new JScrollPane(resultadosPanel);
        scrollResultadosPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        
        //Creacion de puntos y ponerlas en layout box
        JPanel Punto1 = new JPanel();
        Punto1.setLayout(new BoxLayout(Punto1, BoxLayout.Y_AXIS));
        
        JPanel Punto2 = new JPanel();
        Punto2.setLayout(new BoxLayout(Punto2, BoxLayout.Y_AXIS));
        
        JPanel Punto3 = new JPanel();
        Punto3.setLayout(new BoxLayout(Punto3, BoxLayout.Y_AXIS));
        
        JPanel Punto4 = new JPanel();
        Punto4.setLayout(new BoxLayout(Punto4, BoxLayout.Y_AXIS));
        
        JPanel Punto5 = new JPanel();
        Punto5.setLayout(new BoxLayout(Punto5, BoxLayout.Y_AXIS));
        
        JPanel Punto6 = new JPanel();
        Punto6.setLayout(new BoxLayout(Punto6, BoxLayout.Y_AXIS));
        
        JPanel Punto7 = new JPanel();
        Punto7.setLayout(new BoxLayout(Punto7, BoxLayout.Y_AXIS));
        
        JPanel Punto8 = new JPanel();
        Punto8.setLayout(new BoxLayout(Punto8, BoxLayout.Y_AXIS));
        
        resultadosPanel.add(Punto1);
        resultadosPanel.add(Punto2);
        resultadosPanel.add(Punto3);
        resultadosPanel.add(Punto4);
        resultadosPanel.add(Punto5);
        resultadosPanel.add(Punto6);
        resultadosPanel.add(Punto7);
        resultadosPanel.add(Punto8);
        
        //Creacion Punto 1        
        JPanel ContenedorPunto1 = new JPanel();
        ContenedorPunto1.setBackground(new java.awt.Color(0,24,47));
        ContenedorPunto1.setLayout(new FlowLayout((int)LEFT_ALIGNMENT));
        
        JLabel punto1 = new JLabel();
        punto1.setText(" Número de partidos");
        punto1.setForeground(new java.awt.Color(249,249,250));
        punto1.setFont((new Font(punto1.getFont().getName(),Font.BOLD,26)));
        punto1.setVerticalAlignment(JLabel.TOP);
        punto1.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel punto1_2 = new JLabel();
        punto1_2.setText(" cargados:");
        punto1_2.setForeground(new java.awt.Color(249,249,250));
        punto1_2.setFont((new Font(punto1_2.getFont().getName(),Font.BOLD,26)));
        punto1_2.setVerticalAlignment(JLabel.TOP);
        punto1_2.setHorizontalAlignment(JLabel.LEFT);
        
        JPanel ContenedorRespuesta1 = new JPanel();
        ContenedorRespuesta1.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta1.setPreferredSize((new java.awt.Dimension(287, 114)));
        ContenedorRespuesta1.setLayout(new BorderLayout(0, 0));
        
        JLabel Respuesta1 = new JLabel();
        Respuesta1.setText(""); //texto va aqui
        Respuesta1.setForeground(new java.awt.Color(0,24,47));
        Respuesta1.setFont((new Font(punto1.getFont().getName(),Font.BOLD,80)));
        Respuesta1.setVerticalAlignment(JLabel.CENTER);
        Respuesta1.setHorizontalAlignment(JLabel.CENTER);
        ContenedorRespuesta1.add(Respuesta1);
        
        ContenedorPunto1.add(punto1);
        ContenedorPunto1.add(punto1_2);
        ContenedorPunto1.add(ContenedorRespuesta1);
        Punto1.add(ContenedorPunto1);
        
        //Creacion Punto 2;
        
        JPanel ContenedorPunto2 = new JPanel();
        ContenedorPunto2.setBackground(new java.awt.Color(2, 42, 83));
        ContenedorPunto2.setLayout(new FlowLayout((int)LEFT_ALIGNMENT));
        
        JLabel punto2 = new JLabel();
        punto2.setText(" Promedio de goles");
        punto2.setForeground(new java.awt.Color(249,249,250));
        punto2.setFont((new Font(punto2.getFont().getName(),Font.BOLD,26)));
        punto2.setVerticalAlignment(JLabel.TOP);
        punto2.setHorizontalAlignment(JLabel.LEFT);
        
        JLabel punto2_2 = new JLabel();
        punto2_2.setText(" por partido:");
        punto2_2.setForeground(new java.awt.Color(249,249,250));
        punto2_2.setFont((new Font(punto2_2.getFont().getName(),Font.BOLD,26)));
        punto2_2.setVerticalAlignment(JLabel.TOP);
        punto2_2.setHorizontalAlignment(JLabel.LEFT);
        
        JPanel ContenedorRespuesta2 = new JPanel();
        ContenedorRespuesta2.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta2.setPreferredSize((new java.awt.Dimension(297, 114)));
        ContenedorRespuesta2.setLayout(new BorderLayout(0, 0));
        
        DecimalFormat df = new DecimalFormat("#.##");
        df.setDecimalSeparatorAlwaysShown(false);
        String numeroTruncado = "3";
        
        JLabel Respuesta2 = new JLabel();
        Respuesta2.setText(numeroTruncado);
        Respuesta2.setForeground(new java.awt.Color(2, 42, 83));
        Respuesta2.setFont((new Font(punto2.getFont().getName(),Font.BOLD,80)));
        Respuesta2.setVerticalAlignment(JLabel.CENTER);
        Respuesta2.setHorizontalAlignment(JLabel.CENTER);
        ContenedorRespuesta2.add(Respuesta2);
        
        ContenedorPunto2.add(punto2);
        ContenedorPunto2.add(punto2_2);
        ContenedorPunto2.add(ContenedorRespuesta2);
        Punto2.add(ContenedorPunto2);
        
        //Creacion Punto 3
        
        JPanel ContenedorPunto3 = new JPanel();
        ContenedorPunto3.setBackground(new java.awt.Color(4, 61, 119));
        ContenedorPunto3.setLayout(new GridLayout(2, 2, 5, 5));
        ContenedorPunto3.setPreferredSize((new java.awt.Dimension(10, 10)));
        
        JLabel punto3 = new JLabel();
        punto3.setText("<html><div style='text-align: center;'>Partido con más goles</div></html>");
        punto3.setForeground(new java.awt.Color(249,249,250));
        punto3.setFont((new Font(punto3.getFont().getName(),Font.BOLD,22)));
        punto3.setVerticalAlignment(JLabel.TOP);
        punto3.setHorizontalAlignment(JLabel.CENTER);
        Border bordePunto3 = BorderFactory.createLineBorder(new java.awt.Color(4, 61, 119), 5);
        punto3.setBorder(bordePunto3);
        
        JLabel punto3_2 = new JLabel();
        punto3_2.setText("<html><div style='text-align: center;'>Partido con menos goles</div></html>");
        punto3_2.setForeground(new java.awt.Color(249,249,250));
        punto3_2.setFont((new Font(punto3_2.getFont().getName(),Font.BOLD,22)));
        punto3_2.setVerticalAlignment(JLabel.TOP);
        punto3_2.setHorizontalAlignment(JLabel.CENTER);
        Border bordepunto3_2 = BorderFactory.createLineBorder(new java.awt.Color(4, 61, 119), 5);
        punto3_2.setBorder(bordepunto3_2);
        
        JPanel ContenedorRespuesta3 = new JPanel();
        ContenedorRespuesta3.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta3.setPreferredSize((new java.awt.Dimension(10, 10)));
        ContenedorRespuesta3.setLayout(new BorderLayout(0, 0));
        Border bordeRespuesta3 = BorderFactory.createLineBorder(new java.awt.Color(4, 61, 119), 5);
        ContenedorRespuesta3.setBorder(bordeRespuesta3);
        
        JPanel ContenedorRespuesta3_2 = new JPanel();
        ContenedorRespuesta3_2.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta3_2.setPreferredSize((new java.awt.Dimension(10, 10)));
        ContenedorRespuesta3_2.setLayout(new BorderLayout(0, 0));
        Border bordeRespuesta3_2 = BorderFactory.createLineBorder(new java.awt.Color(4, 61, 119), 5);
        ContenedorRespuesta3_2.setBorder(bordeRespuesta3_2);
        
        /*JLabel respuesta31 = new JLabel();
        String VRespuesta3[] = null;
        respuesta31.setText("<html><div style='text-align: center;'>"+VRespuesta3[0] + " <br/>" + VRespuesta3[1]+ " <br/>" + VRespuesta3[2]+ " <br/>" + VRespuesta3[3]+"</div></html>");
        respuesta31.setForeground(new java.awt.Color(4, 61, 119));
        respuesta31.setFont((new Font(respuesta31.getFont().getName(),Font.BOLD,18)));
        respuesta31.setVerticalAlignment(JLabel.CENTER);
        respuesta31.setHorizontalAlignment(JLabel.CENTER);*/
        
        /*JLabel respuesta32 = new JLabel();
        String V2Respuesta32[] = null;
        respuesta32.setText("<html><div style='text-align: center;'>"+V2Respuesta32[0] + " <br/>" + V2Respuesta32[1]+ " <br/>" + V2Respuesta32[2]+ " <br/>" + V2Respuesta32[3]+"</div></html>");
        respuesta32.setForeground(new java.awt.Color(4, 61, 119));
        respuesta32.setFont((new Font(respuesta32.getFont().getName(),Font.BOLD,18)));
        respuesta32.setVerticalAlignment(JLabel.CENTER);
        respuesta32.setHorizontalAlignment(JLabel.CENTER);*/
        
        //ContenedorRespuesta3.add(respuesta31);
        //ContenedorRespuesta3_2.add(respuesta32);
        ContenedorPunto3.add(punto3);
        ContenedorPunto3.add(punto3_2);
        ContenedorPunto3.add(ContenedorRespuesta3);
        ContenedorPunto3.add(ContenedorRespuesta3_2);
        Punto3.add(ContenedorPunto3);
        
        //Creacion Punto 4        
        JPanel ContenedorPunto4 = new JPanel();
        ContenedorPunto4.setBackground(new java.awt.Color(7, 80, 155));
        ContenedorPunto4.setLayout(new GridLayout(2, 2, 5, 5));
        ContenedorPunto4.setPreferredSize((new java.awt.Dimension(10, 10)));
        
        JLabel punto4 = new JLabel();
        punto4.setText("<html><div style='text-align: center;'>Numero de ganadores</div></html>");
        punto4.setForeground(new java.awt.Color(249,249,250));
        punto4.setFont((new Font(punto4.getFont().getName(),Font.BOLD,22)));
        punto4.setVerticalAlignment(JLabel.TOP);
        punto4.setHorizontalAlignment(JLabel.CENTER);
        Border bordePunto4 = BorderFactory.createLineBorder(new java.awt.Color(7, 80, 155), 5);
        punto4.setBorder(bordePunto4);
        
        JLabel punto4_2 = new JLabel();
        punto4_2.setText("<html><div style='text-align: center;'>Numero de empates</div></html>");
        punto4_2.setForeground(new java.awt.Color(249,249,250));
        punto4_2.setFont((new Font(punto4_2.getFont().getName(),Font.BOLD,22)));
        punto4_2.setVerticalAlignment(JLabel.TOP);
        punto4_2.setHorizontalAlignment(JLabel.CENTER);
        Border bordepunto4_2 = BorderFactory.createLineBorder(new java.awt.Color(7, 80, 155), 5);
        punto4_2.setBorder(bordepunto4_2);
        
        JPanel ContenedorRespuesta4 = new JPanel();
        ContenedorRespuesta4.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta4.setPreferredSize((new java.awt.Dimension(10, 10)));
        ContenedorRespuesta4.setLayout(new BorderLayout(0, 0));
        Border bordeRespuesta4 = BorderFactory.createLineBorder(new java.awt.Color(7, 80, 155), 5);
        ContenedorRespuesta4.setBorder(bordeRespuesta4);
        
        JPanel ContenedorRespuesta4_2 = new JPanel();
        ContenedorRespuesta4_2.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta4_2.setPreferredSize((new java.awt.Dimension(10, 10)));
        ContenedorRespuesta4_2.setLayout(new BorderLayout(0, 0));
        Border bordeRespuesta4_2 = BorderFactory.createLineBorder(new java.awt.Color(7, 80, 155), 5);
        ContenedorRespuesta4_2.setBorder(bordeRespuesta4_2);
        
        JLabel respuesta41 = new JLabel();
        respuesta41.setText("<html><div style='text-align: center;'>"+0+"</div></html>");
        respuesta41.setForeground(new java.awt.Color(7, 80, 155));
        respuesta41.setFont((new Font(respuesta41.getFont().getName(),Font.BOLD,80)));
        respuesta41.setVerticalAlignment(JLabel.CENTER);
        respuesta41.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel respuesta42 = new JLabel();
        respuesta42.setText("<html><div style='text-align: center;'>"+0+"</div></html>");
        respuesta42.setForeground(new java.awt.Color(7, 80, 155));
        respuesta42.setFont((new Font(respuesta42.getFont().getName(),Font.BOLD,80)));
        respuesta42.setVerticalAlignment(JLabel.CENTER);
        respuesta42.setHorizontalAlignment(JLabel.CENTER);
        
        ContenedorRespuesta4.add(respuesta41);
        ContenedorRespuesta4_2.add(respuesta42);
        ContenedorPunto4.add(punto4);
        ContenedorPunto4.add(punto4_2);
        ContenedorPunto4.add(ContenedorRespuesta4);
        ContenedorPunto4.add(ContenedorRespuesta4_2);
        Punto4.add(ContenedorPunto4);
        
        //Creacion Punto 5
        
        JPanel ContenedorPunto5 = new JPanel();
        ContenedorPunto5.setBackground(new java.awt.Color(9, 98, 191));
        ContenedorPunto5.setLayout(new GridLayout(2, 2, 5, 5));
        ContenedorPunto5.setPreferredSize((new java.awt.Dimension(10, 10)));
        
        JLabel punto5 = new JLabel();
        punto5.setText("<html><div style='text-align: center;'>Selcción con más goles</div></html>");
        punto5.setForeground(new java.awt.Color(249,249,250));
        punto5.setFont((new Font(punto5.getFont().getName(),Font.BOLD,22)));
        punto5.setVerticalAlignment(JLabel.TOP);
        punto5.setHorizontalAlignment(JLabel.CENTER);
        Border bordePunto5 = BorderFactory.createLineBorder(new java.awt.Color(9, 98, 191), 5);
        punto5.setBorder(bordePunto5);
        
        JLabel punto5_2 = new JLabel();
        punto5_2.setText("<html><div style='text-align: center;'>Selcción con menos goles</div></html>");
        punto5_2.setForeground(new java.awt.Color(249,249,250));
        punto5_2.setFont((new Font(punto5_2.getFont().getName(),Font.BOLD,22)));
        punto5_2.setVerticalAlignment(JLabel.TOP);
        punto5_2.setHorizontalAlignment(JLabel.CENTER);
        Border bordepunto5_2 = BorderFactory.createLineBorder(new java.awt.Color(9, 98, 191), 5);
        punto5_2.setBorder(bordepunto5_2);
        
        JPanel ContenedorRespuesta5 = new JPanel();
        ContenedorRespuesta5.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta5.setPreferredSize((new java.awt.Dimension(10, 10)));
        ContenedorRespuesta5.setLayout(new BorderLayout(0, 0));
        Border bordeRespuesta5 = BorderFactory.createLineBorder(new java.awt.Color(9, 98, 191), 5);
        ContenedorRespuesta5.setBorder(bordeRespuesta5);
        
        JPanel ContenedorRespuesta5_2 = new JPanel();
        ContenedorRespuesta5_2.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta5_2.setPreferredSize((new java.awt.Dimension(10, 10)));
        ContenedorRespuesta5_2.setLayout(new BorderLayout(0, 0));
        Border bordeRespuesta5_2 = BorderFactory.createLineBorder(new java.awt.Color(9, 98, 191), 5);
        ContenedorRespuesta5_2.setBorder(bordeRespuesta5_2);
        
        JLabel respuesta51 = new JLabel();
        respuesta51.setText("<html><div style='text-align: center;'>"+0+"</div></html>");
        respuesta51.setForeground(new java.awt.Color(9, 98, 191));
        respuesta51.setFont((new Font(respuesta51.getFont().getName(),Font.BOLD,32)));
        respuesta51.setVerticalAlignment(JLabel.CENTER);
        respuesta51.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel respuesta52 = new JLabel();
        respuesta52.setText("<html><div style='text-align: center;'>"+0+"</div></html>");
        respuesta52.setForeground(new java.awt.Color(9, 98, 191));
        respuesta52.setFont((new Font(respuesta52.getFont().getName(),Font.BOLD,32)));
        respuesta52.setVerticalAlignment(JLabel.CENTER);
        respuesta52.setHorizontalAlignment(JLabel.CENTER);
        
        ContenedorRespuesta5.add(respuesta51);
        ContenedorRespuesta5_2.add(respuesta52);
        ContenedorPunto5.add(punto5);
        ContenedorPunto5.add(punto5_2);
        ContenedorPunto5.add(ContenedorRespuesta5);
        ContenedorPunto5.add(ContenedorRespuesta5_2);
        Punto5.add(ContenedorPunto5);
        
        //Creacion Punto 6
        JPanel ContenedorPunto6 = new JPanel();
        ContenedorPunto6.setBackground(new java.awt.Color(11, 117, 227));
        ContenedorPunto6.setLayout(new GridLayout(2, 2, 5, 5));
        ContenedorPunto6.setPreferredSize((new java.awt.Dimension(10, 10)));
        
        JLabel punto6 = new JLabel();
        punto6.setText("<html><div style='text-align: center;'>Selección con más puntos</div></html>");
        punto6.setForeground(new java.awt.Color(249,249,250));
        punto6.setFont((new Font(punto6.getFont().getName(),Font.BOLD,22)));
        punto6.setVerticalAlignment(JLabel.TOP);
        punto6.setHorizontalAlignment(JLabel.CENTER);
        Border bordePunto6 = BorderFactory.createLineBorder(new java.awt.Color(11, 117, 227), 5);
        punto6.setBorder(bordePunto6);
        
        JLabel punto6_2 = new JLabel();
        punto6_2.setText("<html><div style='text-align: center;'>Selección con menos puntos</div></html>");
        punto6_2.setForeground(new java.awt.Color(249,249,250));
        punto6_2.setFont((new Font(punto6_2.getFont().getName(),Font.BOLD,22)));
        punto6_2.setVerticalAlignment(JLabel.TOP);
        punto6_2.setHorizontalAlignment(JLabel.CENTER);
        Border bordepunto6_2 = BorderFactory.createLineBorder(new java.awt.Color(11, 117, 227), 5);
        punto6_2.setBorder(bordepunto6_2);
        
        JPanel ContenedorRespuesta6 = new JPanel();
        ContenedorRespuesta6.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta6.setPreferredSize((new java.awt.Dimension(10, 10)));
        ContenedorRespuesta6.setLayout(new BorderLayout(0, 0));
        Border bordeRespuesta6 = BorderFactory.createLineBorder(new java.awt.Color(11, 117, 227), 5);
        ContenedorRespuesta6.setBorder(bordeRespuesta6);
        
        JPanel ContenedorRespuesta6_2 = new JPanel();
        ContenedorRespuesta6_2.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta6_2.setPreferredSize((new java.awt.Dimension(10, 10)));
        ContenedorRespuesta6_2.setLayout(new BorderLayout(0, 0));
        Border bordeRespuesta6_2 = BorderFactory.createLineBorder(new java.awt.Color(11, 117, 227), 5);
        ContenedorRespuesta6_2.setBorder(bordeRespuesta6_2);
        
        JLabel respuesta61 = new JLabel();
        respuesta61.setText("<html><div style='text-align: center;'>"+0+"</div></html>");
        respuesta61.setForeground(new java.awt.Color(11, 117, 227));
        respuesta61.setFont((new Font(respuesta61.getFont().getName(),Font.BOLD,32)));
        respuesta61.setVerticalAlignment(JLabel.CENTER);
        respuesta61.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel respuesta62 = new JLabel();
        respuesta62.setText("<html><div style='text-align: center;'>"+0+"</div></html>");
        respuesta62.setForeground(new java.awt.Color(11, 117, 227));
        respuesta62.setFont((new Font(respuesta62.getFont().getName(),Font.BOLD,32)));
        respuesta62.setVerticalAlignment(JLabel.CENTER);
        respuesta62.setHorizontalAlignment(JLabel.CENTER);
        
        ContenedorRespuesta6.add(respuesta61);
        ContenedorRespuesta6_2.add(respuesta62);
        ContenedorPunto6.add(punto6);
        ContenedorPunto6.add(punto6_2);
        ContenedorPunto6.add(ContenedorRespuesta6);
        ContenedorPunto6.add(ContenedorRespuesta6_2);
        Punto6.add(ContenedorPunto6);
        
        //Creacion Punto 7
        JPanel ContenedorPunto7 = new JPanel();
        ContenedorPunto7.setBackground(new java.awt.Color(18, 119, 217));
        ContenedorPunto7.setLayout(new GridLayout(2, 2, 5, 5));
        ContenedorPunto7.setPreferredSize((new java.awt.Dimension(10, 10)));
        
        JLabel punto7 = new JLabel();
        punto7.setText("<html><div style='text-align: center;'>Continente con más goles</div></html>");
        punto7.setForeground(new java.awt.Color(249,249,250));
        punto7.setFont((new Font(punto7.getFont().getName(),Font.BOLD,22)));
        punto7.setVerticalAlignment(JLabel.TOP);
        punto7.setHorizontalAlignment(JLabel.CENTER);
        Border bordePunto7 = BorderFactory.createLineBorder(new java.awt.Color(18, 119, 217), 5);
        punto7.setBorder(bordePunto7);
        
        JLabel punto7_2 = new JLabel();
        punto7_2.setText("<html><div style='text-align: center;'>Continente con menos goles</div></html>");
        punto7_2.setForeground(new java.awt.Color(249,249,250));
        punto7_2.setFont((new Font(punto7_2.getFont().getName(),Font.BOLD,22)));
        punto7_2.setVerticalAlignment(JLabel.TOP);
        punto7_2.setHorizontalAlignment(JLabel.CENTER);
        Border bordepunto7_2 = BorderFactory.createLineBorder(new java.awt.Color(18, 119, 217), 5);
        punto7_2.setBorder(bordepunto7_2);
        
        JPanel ContenedorRespuesta7 = new JPanel();
        ContenedorRespuesta7.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta7.setPreferredSize((new java.awt.Dimension(10, 10)));
        ContenedorRespuesta7.setLayout(new BorderLayout(0, 0));
        Border bordeRespuesta7 = BorderFactory.createLineBorder(new java.awt.Color(18, 119, 217), 5);
        ContenedorRespuesta7.setBorder(bordeRespuesta7);
        
        JPanel ContenedorRespuesta7_2 = new JPanel();
        ContenedorRespuesta7_2.setBackground(new java.awt.Color(249,249,250));
        ContenedorRespuesta7_2.setPreferredSize((new java.awt.Dimension(10, 10)));
        ContenedorRespuesta7_2.setLayout(new BorderLayout(0, 0));
        Border bordeRespuesta7_2 = BorderFactory.createLineBorder(new java.awt.Color(18, 119, 217), 5);
        ContenedorRespuesta7_2.setBorder(bordeRespuesta7_2);
        
        JLabel respuesta71 = new JLabel();
        respuesta71.setText("<html><div style='text-align: center;'>"+0+"</div></html>");
        respuesta71.setForeground(new java.awt.Color(18, 119, 217));
        respuesta71.setFont((new Font(respuesta71.getFont().getName(),Font.BOLD,22)));
        respuesta71.setVerticalAlignment(JLabel.CENTER);
        respuesta71.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel respuesta72 = new JLabel();
        respuesta72.setText("<html><div style='text-align: center;'>"+0+"</div></html>");
        respuesta72.setForeground(new java.awt.Color(18, 119, 217));
        respuesta72.setFont((new Font(respuesta72.getFont().getName(),Font.BOLD,22)));
        respuesta72.setVerticalAlignment(JLabel.CENTER);
        respuesta72.setHorizontalAlignment(JLabel.CENTER);
        
        ContenedorRespuesta7.add(respuesta71);
        ContenedorRespuesta7_2.add(respuesta72);
        ContenedorPunto7.add(punto7);
        ContenedorPunto7.add(punto7_2);
        ContenedorPunto7.add(ContenedorRespuesta7);
        ContenedorPunto7.add(ContenedorRespuesta7_2);
        Punto7.add(ContenedorPunto7);
        
        jPanelMain.removeAll();
        //jPanelMain.add(a);
        
        jPanelMain.add(scrollResultadosPanel, BorderLayout.PAGE_START);
        
        jPanelMain.repaint();
        jPanelMain.revalidate();        
    }

    
    
    public void pintarTablaResultados() {

        JTable table = conn.consulta("SELECT * FROM transacciones_tse");
        table.setRowHeight(30);
        
        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize( jPanelRight.getPreferredSize());
        
        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(scrollPane);
        
        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }
    
    
    private void pintarPanelDerecho() {
        
        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(620, 540)));
        //jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());
        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        //jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(620, 540)));
        jPanelRight.setMaximumSize((new java.awt.Dimension(620, 540)));
    }
    
    /**
     * Función que permite pinta la barra azul del contenedor de contenidos. Barra azul que permite indicar 
     * en que sección que se encuentra navegando el usuario.
     */
    private void pintarLabelTop() {
        /*jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");*/
        
        JLabel vacioTopLabel = new JLabel();
        /*jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(18, 119, 217));
        //jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(620, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());*/
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}