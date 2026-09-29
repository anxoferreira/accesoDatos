package repaso.ejercicio2.banco;

public class OtraBanco {
    
    public static void main(String[] args) {
        
        Cuenta c = new Cuenta();
       
        //saldo no se puede acceder desde otra clase
        //System.out.println(c.saldo);
        System.out.println(c.codigo);
        System.out.println(c.oficina);
        System.out.println(c.titular);

    }

}
