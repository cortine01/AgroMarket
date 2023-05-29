package proyectomundial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import proyectomundial.DAO.AuditoriaDAO;
import proyectomundial.DAO.ResultadoDAO;
import proyectomundial.DAO.SeleccionDAO;
import proyectomundial.model.Resultados;
import proyectomundial.model.Seleccion;

public class GUIManual extends JFrame {
    
    /*
    
        Bryan Esteban Cortine Pertuz
        Anny Camila Jaimes Esquibel
    
    */

    SeleccionDAO seleccionDAO = new SeleccionDAO();
    ResultadoDAO resultadoDAO = new ResultadoDAO();
    AuditoriaDAO auditoriaDAO = new AuditoriaDAO();
    
    
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
    
    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;
    
    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;
    
    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;
    
    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;
    
    private JPanel jPanelMenuAuditoria;
    private JLabel btnAuditoria;
        
    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;
    
    private JPanel jPanelMain;
    
    
    public GUIManual() {
        
        // Se inician los componentes gráficos
        initComponents();
        
        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionHome();
        
    }
    
    
    private void initComponents() {

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();
        
        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();
        
        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();
        
        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();
        
        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();
        
        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();
        
        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();
        
        jPanelMenuAuditoria = new JPanel();
        btnAuditoria = new JLabel();
        
        // Pinta el logo de la aplicación
        pintarLogo();
        
        // Pinta la opción de menú del Home
        pintarMenuHome();
        
        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();
        
        // Pinta la opción de Menú de los resultados
        pintarMenuResultados();
        
        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();
        
        // Pinta la opción de Menú del dahboard de resultados
        pintarMenuDashboardRes();
        
        pintarAuditoria();
        
        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();
        
        
        
        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
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
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/Easports_fifa_logo.svg.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);
        
    }
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación del HOME
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar la página de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(17, 41, 63));
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
        jLabelTop.setText("Home");
        auditoriaDAO.Incrementador("Home");
        //jLabelTopDescription.setText("Bievenido al sistema de gestión de mundiales de fútbol");

        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();

        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/home.jpg"))); // NOI18N
        //imageHome.setPreferredSize(new java.awt.Dimension(810, 465));
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
    private void pintarMenuSelecciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);
        
        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Selecciones");
                accionSelecciones();
            }
        });
    }
    
    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de navegación Selecciones
     * Permite ver la lista de selecciones que se encuentran cargadas en la aplicación. 
     * Si la lista de selecciones en vacía, muestra un botón que permite cargar un archivo CSV
     * con la información de las selelecciones
     */
    private void accionSelecciones() {
        jLabelTop.setText("Selecciones");
        auditoriaDAO.Incrementador("Selecciones");
        selecciones = seleccionDAO.getSeleccionesMatriz();
        
        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();

            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
            seleccionesPanel.add(notSelecciones);

            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            seleccionesPanel.add(cargarFile);
            cargarFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    cargarFileSelecciones();
                }
            });

            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        }
        // Si hay selecciones cargadas, llama el método que permite pintar la tabla de selecciones
        else {
            pintarTablaSelecciones();
        }
    }
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación de RESULTADOS
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar los diferentes resultados de los partidos de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);
        
        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accionResultados();
            }
        });
    }
    
    
    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de navegación Resultados
     * Permite ver la lista de resultados que se encuentran cargadas en la aplicación. 
     * Si la lista de resultados en vacía, muestra un botón que permite cargar un archivo CSV
     * con la información de los resultados
     */
    private void accionResultados() {
        jLabelTop.setText("Resultados");
        auditoriaDAO.Incrementador("Resultados");
        resultados = resultadoDAO.getResultadosMatriz();

        // Si no hay resultados cargados, muestra el botón de carga de resultados
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();

            if (resultados == null) {

                JLabel notResultados = new JLabel();
                notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
                resultadosPanel.add(notResultados);

                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                resultadosPanel.add(cargarFile);
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileResultados();
                    }
                });
            }

            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        }
        // Si hay ressultados cargados, llama el método que permite pintar la tabla de resultados
        else {
            pintarTablaResultados();
        }
    }
    
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación de Dashboard de Selecciones
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar los diferentes datos que será extraidos de la información de 
     * las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {
        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);
        
        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Selecciones");
                accionDashboardSel();
            }
        });
    }
    
    
    /**
     * TRABAJO DEL ESTUDIANTE
     * Se debe módificar este método para poder calcular y pintar las diferentes informaciones que son solicitadas
     * Revise el proceso que se siguen en los demás métodos para poder actualizar la información de los paneles
     */
    private void accionDashboardSel() {
        
        jLabelTop.setText("Dashboard Selecciones");
        
        auditoriaDAO.Incrementador("Dash Selecciones");
        
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
        
        JPanel Columna1 = new JPanel();
        Columna1.setLayout(new BoxLayout(Columna1, BoxLayout.Y_AXIS));
        
        JPanel Columna2 = new JPanel();
        Columna2.setLayout(new BoxLayout(Columna2, BoxLayout.Y_AXIS));
        
        JLabel label = new JLabel();
        label.setText("Total de selecciones Cargadas");
        Columna1.add(label);
        
        
        
        JLabel Respuesta1 = new JLabel();
        Respuesta1.setText(""+seleccionDAO.getCantidadSelecciones());
        Columna1.add(Respuesta1);
        
        
        JLabel label2 = new JLabel();
        label2.setText("Número de selecciones por continente");
        Columna1.add(label2);
        
        
        String[] columnNames = {"Continente", "Cantidad Selecciones"};
        String[][] Relleno = seleccionDAO.cantidadSeleccionesContinente();

        JTable table = new JTable(Relleno, columnNames);
        //table.setRowHeight(20);
        
        JLabel label3 = new JLabel();
        label3.setText("Cantidad de nacionalidades diferentes de los directores técnicos");
        Columna2.add(label3);
        
        
        
        JLabel Respuesta3 = new JLabel();
        Respuesta3.setText(""+seleccionDAO.getCantidadNacionalidades());
        Columna2.add(Respuesta3);
        
        JLabel label4 = new JLabel();
        label4.setText("Ranking de nacionalidades de directores técnicos");
        Columna2.add(label4);
        
        String[] Respuesta4Columnas = {"Nacionalidad", "Cantidad"};
        String[][] Respuesta4Relleno = seleccionDAO.mayorCantidadNacionalidad();
        JTable Respuesta4 = new JTable(Respuesta4Relleno, Respuesta4Columnas);
        ///////Aqui va el grafico
        
        Columna2.add(crearRankingGrafica(Respuesta4Relleno));
        
        Respuesta3.setText(""+seleccionDAO.getCantidadNacionalidades());
        
        JScrollPane scrollPaneRespuesta4 = new JScrollPane(Respuesta4);
        scrollPaneRespuesta4.setPreferredSize(Respuesta4.getPreferredSize());
        scrollPaneRespuesta4.setMaximumSize((new java.awt.Dimension(620, 71)));
        Columna2.add(scrollPaneRespuesta4);
        
        
        seleccionesPanel.setLayout(new GridLayout(0, 2, 0, 0));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize( jPanelRight.getPreferredSize());
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(table.getPreferredSize());
        scrollPane.setMaximumSize((new java.awt.Dimension(620, 135)));
        //seleccionesPanel.add(form);
        Columna1.add(scrollPane);
        //experimento.setText("a ");
        seleccionesPanel.add(Columna1);
        seleccionesPanel.add(Columna2);
        
        
        jPanelMain.removeAll();
        //jPanelMain.add(a);
        
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        
        jPanelMain.repaint();
        jPanelMain.revalidate();        
    }
    
    private ChartPanel crearRankingGrafica(String [][] contenido){
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        
        for(int i = 0; i < contenido.length; i++) {
            //System.out.println(contenido[i][0]);
            datos.setValue(Integer.parseInt(contenido[i][1]), ("#"+i), contenido[i][0]);
        }
        
        JFreeChart grafico_barras = ChartFactory.createBarChart3D(
        "Ranking de nacionalidades de directores técnicos",
        "Directores Tecnicos",
        "Cantidad",
        datos,
        PlotOrientation.VERTICAL,
        true,
        true,
        false
        );
        
        ChartPanel panel = new ChartPanel(grafico_barras);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(620,135));
        
        return panel;
    }
    
    
    
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la opción de navegación de Dashboard de Resultados
     * Define estilos, etiquetas, iconos que decoran la opción del Menú. 
     * Esta opción de Menu permite mostrar los diferentes datos que será extraidos de la información de 
     * los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(17, 41, 63));
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
        
        jLabelTop.setText("Dashboard Resultados");
        
        auditoriaDAO.Incrementador("Dash Resultados");
        
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
        resultadosPanel.setLayout(new GridLayout(0, 2, 0, 0));
        resultadosPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        resultadosPanel.setMaximumSize(jPanelRight.getPreferredSize());
        
        //Creacion de Columnas y ponerlas en layout box
        JPanel Columna1 = new JPanel();
        Columna1.setLayout(new BoxLayout(Columna1, BoxLayout.Y_AXIS));
        JPanel Columna2 = new JPanel();
        Columna2.setLayout(new BoxLayout(Columna2, BoxLayout.Y_AXIS));
        resultadosPanel.add(Columna1);
        resultadosPanel.add(Columna2);
        
        //Creacion Punto 1
        JLabel punto1 = new JLabel();
        punto1.setText("Número de partidos cargados");
        Columna1.add(punto1);
        
        JLabel respuesta1 = new JLabel();
        respuesta1.setText(""+resultadoDAO.getCantidadResultados());
        Columna1.add(respuesta1);
        
        //Creacion Punto 2
        JLabel punto2 = new JLabel();
        punto2.setText("Promedio de goles por partido");
        Columna1.add(punto2);
        
        JLabel respuesta2 = new JLabel();
        respuesta2.setText(""+resultadoDAO.getPromedioGoles());
        Columna1.add(respuesta2);
        
        //Creacion Punto 3
        JLabel punto3 = new JLabel();
        punto3.setText("Partido con más goles y partido con menos goles");
        Columna1.add(punto3);
        
        JLabel respuesta31 = new JLabel();
        String VRespuesta3[] = resultadoDAO.getpartidoMayorGoles();
        respuesta31.setText(VRespuesta3[0] + " " + VRespuesta3[1]+ " " + VRespuesta3[2]+ " " + VRespuesta3[3]);
        Columna1.add(respuesta31);
        
        JLabel respuesta32 = new JLabel();
        String V2Respuesta32[] = resultadoDAO.getpartidoMenorGoles();
        respuesta32.setText(V2Respuesta32[0] + " " + V2Respuesta32[1]+ " " + V2Respuesta32[2]+ " " + V2Respuesta32[3]);
        
        Columna1.add(respuesta32);
        
        //Creacion Punto 4
        JLabel punto4 = new JLabel();
        punto4.setText("Numero de ganadores y empates");
        Columna1.add(punto4);
        
        JLabel respuesta4 = new JLabel();
        JLabel respuesta4_2 = new JLabel();
        respuesta4.setText("Ganadores: "+resultadoDAO.getCantidadEmpateGanador()[1]);
        respuesta4_2.setText("Empate: "+resultadoDAO.getCantidadEmpateGanador()[0]);
        Columna1.add(respuesta4_2);
        Columna1.add(respuesta4);
        
        //Creacion Punto 5
        JLabel punto5 = new JLabel();
        punto5.setText("Selcción o selecciones con más goles y con menos goles");
        Columna1.add(punto5);
        
        JLabel respuesta5 = new JLabel();
        respuesta5.setText(""+resultadoDAO.getSeleccionMayorGoles());
        Columna1.add(respuesta5);
        
        JLabel respuesta5_2 = new JLabel();
        respuesta5_2.setText(""+resultadoDAO.getSeleccionMenorGoles());
        Columna1.add(respuesta5_2);
        
        //Creacion Punto 6
        JLabel punto6 = new JLabel();
        punto6.setText("Selección con más puntos y menos puntos");
        Columna1.add(punto6);
        
        JLabel respuesta6 = new JLabel();
        respuesta6.setText(""+resultadoDAO.getSeleccionMayorPuntos());
        Columna1.add(respuesta6);
        
        JLabel respuesta6_2 = new JLabel();
        respuesta6_2.setText(""+resultadoDAO.getSeleccionMenorPuntos());
        Columna1.add(respuesta6_2);
        
        //Creacion Punto 7
        JLabel punto7 = new JLabel();
        punto7.setText("Continente o continentes con más goles y menos goles");
        Columna1.add(punto7);
        
        JLabel respuesta7 = new JLabel();
        respuesta7.setText(""+resultadoDAO.getContinenteMayorGoles());
        Columna1.add(respuesta7);
        
        JLabel respuesta7_2 = new JLabel();
        respuesta7_2.setText(""+resultadoDAO.getContinenteMenorGoles());
        Columna1.add(respuesta7_2);
        
        jPanelMain.removeAll();
        //jPanelMain.add(a);
        
        jPanelMain.add(resultadosPanel, BorderLayout.PAGE_START);
        
        jPanelMain.repaint();
        jPanelMain.revalidate();        
    }
    
    private void pintarAuditoria() {
        btnAuditoria.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnAuditoria.setText("Auditoria");
        btnAuditoria.setForeground(new java.awt.Color(255, 255, 255));
        
        JLabel vacioResultados = new JLabel();
        jPanelMenuAuditoria.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuAuditoria.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuAuditoria.setLayout(new BorderLayout(15, 0));
        jPanelMenuAuditoria.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuAuditoria.add(btnAuditoria, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuAuditoria);
        
        btnAuditoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accionAuditoria();
            }
        });
    }
    
    private void accionAuditoria() {
         
        jLabelTop.setText("Auditoria");
        
        JPanel Contenido = new JPanel();
        Contenido.setLayout(new BoxLayout(Contenido, BoxLayout.Y_AXIS));
        
        JLabel label4 = new JLabel();
        label4.setText("Cantidad de visitas a las paginas");
        Contenido.add(label4);
        
        String[] Respuesta4Columnas = {"Pagina", "contador"};
        String[][] Respuesta4Relleno = auditoriaDAO.Mostrar();
        JTable Respuesta4 = new JTable(Respuesta4Relleno, Respuesta4Columnas);
        Contenido.add(Respuesta4);
        
        JScrollPane scrollPaneRespuesta4 = new JScrollPane(Respuesta4);
        scrollPaneRespuesta4.setPreferredSize((new java.awt.Dimension(620, 103)));
        scrollPaneRespuesta4.setMaximumSize((new java.awt.Dimension(620, 103)));
        Contenido.add(scrollPaneRespuesta4);
        
        jPanelMain.removeAll();
        jPanelMain.add(Contenido, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }
    
    /**
     * Función que permite darle estilos y agregar los componentes gráficos del contendor de la parte 
     * izquierda de la interfaz, dónde se visulaiza el menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);
        
        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));
        
        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(0, 24, 47));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize( jPanelLeft.getPreferredSize());
    }
    
    
    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en cada una de sus líneas
     * El contenido del archivo es procesado y cargado en la matriz de selecciones. Una vez la información se carga 
     * en la atriz, se hace un llamado a la función pintarTablaSelecciones() que se encarga de pintar en la interfaz 
     * una tabla con la información almacenada en la matriz de selecciones
     */
    public void cargarFileSelecciones() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();
            
            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);
            
            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();
            
            // Se leen cada unas de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Seleccion seleccion = new Seleccion(columns[1], columns[2], columns[3], columns[4]);
                if(seleccionDAO.registrarSeleccion(seleccion)) {
                    System.out.println("Seleccion " + seleccion.getNombre() + " registrada");
                }
                else {
                    System.out.println("Error " + seleccion.getNombre());
                }
            }

            selecciones = seleccionDAO.getSeleccionesMatriz();
            pintarTablaSelecciones();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }
    
    /**
     * Función que se encarga de pinta la tabla con la información de las selelceciones que fue cargada previamente
     * La tabla tiene definido un encabezado con las siguentes columnas: 
     * {"ID","Selección", "Continente", "DT", "Nacionalidad DT"}
     * Columnas que se corresponden son la información que fue leida desde el archivo csv
     */
    public void pintarTablaSelecciones() {

        String[] columnNames = {"Selección", "Continente", "DT", "Nacionalidad DT"};
        JTable table = new JTable(selecciones, columnNames);
        table.setRowHeight(30);
        
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));
        
        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);
        
        JTextField field = new JTextField();
        form.add(field);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));
        
        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);
        buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarSelecciones(field.getText());
            }
        });
        
        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        limpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //selecciones = seleccionDAO.getSeleccionesMatriz();
                pintarTablaSelecciones();
            }
        });
        form.add(panelBotones);
        
        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize( jPanelRight.getPreferredSize());
        
        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);
        
        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }
    
    private void buscarSelecciones(String text) {
        if(!text.isEmpty()) {
            selecciones = seleccionDAO.getSeleccionesMatrizBuscado(text);
            if(selecciones != null) {
                pintarTablaSelecciones();
                System.out.println("HEcho");
            } else {
                JOptionPane.showMessageDialog(null, "NO se encuentra lo que buscas", "Error", JOptionPane.ERROR_MESSAGE);

            }
            
        } else {
            System.out.println("Esta Vacio");
        }
        
        selecciones = seleccionDAO.getSeleccionesMatriz();
    }
    
    
    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados y cargarlo 
     * sobre la matriz resultados que se tiene definida cómo variable global. 
     * Luego de cargar los datos en la matriz, se llama la función pintarTablaResultados() que se encarga 
     * de visulizar el contenido de la matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();
            
            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();
            
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                
                String line = entrada.nextLine();
                String[] columns = line.split(",");
                
                Resultados resultado = new Resultados(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6]);
                if(resultadoDAO.registrarResultado(resultado)) {
                    System.out.println("Resultado " + resultado.getLocal() + " registrada");
                } else {
                    System.out.println("Error " + resultado.getLocal());
                }
            }
            
            resultados = resultadoDAO.getResultadosMatriz();
            pintarTablaResultados();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }
     
    
    /**
     * Función que se encarga de pintar la tabla con la información de los resultados que fue cargada previamente
     * La tabla tiene definido un encabezado con las siguentes columnas: 
     * {"Grupo","Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"}
     * Columnas que se corresponden son la información que fue leida desde el archivo csv
     */
    public void pintarTablaResultados() {

        String[] columnNames = {"Grupo","Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        JTable table = new JTable(resultados, columnNames);
        table.setRowHeight(30);
        
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));
        
        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);
        
        JTextField field = new JTextField();
        form.add(field);
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));
        
        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);
        buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                buscarResultados(field.getText());
                System.out.println("Buscar");
            }
        }); 
        
        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        
        limpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("limpiar");
                pintarTablaResultados();
            }
        }); 
        form.add(panelBotones);
        
        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize( jPanelRight.getPreferredSize());
        
        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);
        
        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }
    
    private void buscarResultados(String text) {
        if(!text.isEmpty()) {
            resultados = resultadoDAO.getResultadosMatrizBuscado(text);
            if(resultados != null) {
                pintarTablaResultados();
                System.out.println("HEcho");
            } else {
                JOptionPane.showMessageDialog(null, "NO se encuentra lo que buscas", "Error", JOptionPane.ERROR_MESSAGE);

            }
            
        } else {
            System.out.println("Esta Vacio");
        }
        
        resultados = resultadoDAO.getResultadosMatriz();
    }
    
    
    /**
     * Función que permite darle estilos y agregar los componentes gráficos del contendor de la parte 
     * derecha de la interfaz, dónde se visulaiza de manera dinámica el contenido de cada una de las funciones
     * que puede realizar el usuario sobre la aplicación. 
     */
    private void pintarPanelDerecho() {
        
        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(620, 420)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());
        
        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(620, 540)));
        jPanelRight.setMaximumSize( jPanelRight.getPreferredSize());
    }
    
    /**
     * Función que permite pinta la barra azul del contenedor de contenidos. Barra azul que permite indicar 
     * en que sección que se encuentra navegando el usuario.
     */
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");
        
        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(18, 119, 217));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(620, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}