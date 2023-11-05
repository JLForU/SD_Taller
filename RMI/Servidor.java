
/*
    Cuando se ejecute el método `getResultado()`:
   	    1). Se llama a la función de suma del `Operador_01`,
   	    2). ese resultado se le envía a la función de multiplicación del `Operador_02`, y
		3). el resultado de esta última lo retorna la función como String.
*/

import java.rmi.NotBoundException ;
import java.rmi.RemoteException ;
import java.rmi.registry.LocateRegistry ;
import java.rmi.registry.Registry ;
import java.rmi.server.UnicastRemoteObject ;


public class Servidor extends UnicastRemoteObject implements Interfaz_Servidor {
    

    // Constructor //
    protected Servidor ( ) throws RemoteException {
        System.out.println ( "Iniciando `Servidor`..." ) ;
    }

    // Arranque de Servidor.
    public static void main ( String[] args ) {
        

        try {

            // Crear el objeto cuyos métodos podrá usar el cliente.
            Servidor S = new Servidor() ;
            
            // Incluir el objeto en el registro del RMI en el puerto 1090,
            // para que el cliente lo pueda encontrar.
            Registry registry = LocateRegistry.createRegistry ( 1090 ) ;
            registry.rebind ( "server" , S ) ;
            System.out.println ( "Objeto 'server' registrado en RMI" ) ;
        
        } catch ( RemoteException e ) {

        	System.out.println ( "Error: " + e ) ;

        }


    }

    // El método `getResultado`: recibe dos enteros y retorna un String.
    public String getResultado ( int a , int b ) throws RemoteException {

        int suma = 0 ;
        int producto = 0 ;
        String resultado = null ;
            
        try {
            
            // Conectarse al servidor y cargar registro de objetos RMI.
            Registry registry = LocateRegistry.getRegistry ( "localhost" , 1091 ) ;
            Registry registry2 = LocateRegistry.getRegistry ( "localhost" , 1092 ) ;
            
            // Buscar el objeto `Operador_01` en el registro,
            // y si lo encuentra, crear el objeto local.
            try {
            	Interfaz_Operador_01 op1 = (Interfaz_Operador_01) registry.lookup ( "Operador_01" ) ;
            	suma = op1.sumar ( a , b ) ;
    	    } catch ( RemoteException re ) {
    	    	System.out.println ( "¡Error Operador_01!" ) ;
    	    	suma = a + b ;
    	    }
            
            // Buscar el objeto `Operador_02` en el registro,
            // y si lo encuentra, crear el objeto local.
            try {
            	Interfaz_Operador_02 op2 = (Interfaz_Operador_02) registry2.lookup ( "Operador_02" ) ;
            	producto = op2.multiplicar ( suma ) ;
            } catch ( RemoteException re ) {
    	    	System.out.println ( "¡Error Operador_02!" ) ;
            	producto = suma * 10 ;
            }
            
            resultado = Integer.toString ( producto ) ;
            
        } catch ( NotBoundException e ) {
            System.out.println ( "No se encontró el objeto en el registro." ) ;
            System.exit(0) ;
        } catch (RemoteException e) {
            System.out.println ( "Error: " + e ) ;
            System.exit(0) ;
        }

        return resultado ;

    }


}

