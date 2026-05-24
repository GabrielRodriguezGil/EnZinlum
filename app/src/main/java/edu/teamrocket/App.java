package edu.teamrocket;

/*
 * Contratos inteligentes con enZinium.
 * 
 * Rick va a organizar un concieto de Los Ricknillos
 * y quiere poner a la venta 100 entradas en nuestra
 * plataforma enZinium.
 * Para ello va a utilizar un contrato inteligente 
 * construido sobre nuestra plataforma.
 * Este contrato recibira instrucciones sobre a quien vender
 * las entradas. El contrato almacenara que usuarios/as 
 * de la plataforma poseen las entradas.
 * Cada usuario/a dipone de una Address /direccion en la
 * plataforma, desde la que gestionar sus enZiniums 
 * y las entradas.
 */

public class App {
     
    public static void main( String[] args )
    {
        /*
         * Crea una Address en nuestro sistema para Rick
         * Genera las claves privada y publica de la direccion
         * El balance de enZinium de su direccion es cero
         * El simbolo del enZinium es EZI
         */

        Address rick = new Address();
        rick.generateKeyPair();

        /*
         * Visualiza la direccion publica de Rick y su balance
         * 
         * Utiliza el metodo hashCode() de PublicKey para 
         * mostrar la PublicKey en consola 
         */
        
        System.out.println("\n" + "Address de Rick" + "\n" + 
                                  "==============="        );
        System.out.println(rick.toString());

        /*
         * Creamos una Address en nuestro sistema para Morty
         * y otra para Jen y visualizamos su direccion publica
         * y balance
         */
        
        Address morty = new Address();
        morty.generateKeyPair();

        Address jen = new Address();
        jen.generateKeyPair();

        System.out.println("\n" + "Address de Morty" + "\n" + 
                                  "==============="        );
        System.out.println(morty.toString());  
        System.out.println("\n" + "Address de Jen" + "\n" + 
                                  "==============="        );
        System.out.println(jen.toString());         

        /*
         * Crea una contrato inteligente de tipo TokenContract 
         * en nuestro sistema para que Rick pueda vender 100 
         * entradas para el concierto de "los Ricknillos". 
         *  
         * El nombre del token (las entradas) es Ricknillos.
         * Su simbolo es RNiLL.
         * 
         * El propietario del contrato es Rick. Asigna su Public Key
         * a la propiedad owner del contrato mediante el contructor
         * de TokenContract.
         * 
         * En la clase TokenContract programa las funciones a utilizar en 
         * el metodo toString():
         * 
         * name() 
         * @return devuelve el nombre del token de forma human-readable (p.e., “US Dollars”).
         * 
         * symbol()
         * @return el nombre del símbolo del token de forma human-readable (p.e., “USD”).
         * 
         * totalSupply()
         * @return el total de unidades de este token que actualmente existen.
         */


    }
}
