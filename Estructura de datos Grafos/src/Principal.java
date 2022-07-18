public class Principal {
    public static void main(String[] args){
        GramETSISI redSocial = new GramETSISI(10);
        Persona persona0 = new Persona("Juan", "123456","Albufera");
        Persona persona1 = new Persona("José", "231465","Princesa");
        Persona persona2 = new Persona("Eva", "321654","Gaztambide");
        Persona persona3 = new Persona("Alicia", "789456","Castellana");
        Persona persona4 = new Persona("Ernesto", "654321","Arboleda");
        Persona persona5 = new Persona("Guillermo", "159267","Arapiles");
        Persona persona6 = new Persona("Alberto", "123456","Gaztambide");
        Persona persona7 = new Persona("Lucas", "231465","Princesa");
        Persona persona8 = new Persona("Paula", "321654","Gran Vía");
        Persona persona9 = new Persona("Clara", "789456","Castellana");

        redSocial.insertaVertice(persona0);
        redSocial.insertaVertice(persona1);
        redSocial.insertaVertice(persona2);
        redSocial.insertaVertice(persona3);
        redSocial.insertaVertice(persona4);
        redSocial.insertaVertice(persona5);
        redSocial.insertaVertice(persona6);
        redSocial.insertaVertice(persona7);
        redSocial.insertaVertice(persona8);
        redSocial.insertaVertice(persona9);

        redSocial.insertaArista(0,1);
        redSocial.insertaArista(0,4);
        redSocial.insertaArista(0,5);
        redSocial.insertaArista(0,3);
        redSocial.insertaArista(1,4);
        redSocial.insertaArista(4,5);
        redSocial.insertaArista(2,9);
        redSocial.insertaArista(2,7);
        redSocial.insertaArista(7,9);
        redSocial.insertaArista(6,8);

        redSocial.mostrarRed();

        System.out.println ("\n----------------------------------------------------");
        System.out.println("Apartado 2.4. Número de grupos.");
        System.out.println ("Hay " + redSocial.contarGrupos() + " grupos");

        System.out.println ("----------------------------------------------------");
        System.out.println("Apartado 2.5. Mostrar amigos.");
        redSocial.mostrarAmigos("Juan");

        System.out.println ("----------------------------------------------------");
        System.out.println("Apartado 2.6. Grupo con mayor número.");
        redSocial.mayorGrupo();
        System.out.println ("----------------------------------------------------");

    }

}
