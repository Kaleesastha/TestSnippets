import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class X509CertLog {

  public static void main(String args[]) throws Exception {
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    FileInputStream in = new FileInputStream(args[0]);
    java.security.cert.Certificate c = cf.generateCertificate(in);
    in.close();

    X509Certificate t = (X509Certificate) c;
    System.out.println("Get Version of certificate : "+t.getVersion());
    System.out.println(t.getSerialNumber().toString(16));
    System.out.println(t.getSubjectDN());
    System.out.println(t.getIssuerDN());
    System.out.println(t.getNotBefore());
    System.out.println(t.getNotAfter());
    System.out.println(t.getSigAlgName());
    byte[] sig = t.getSignature();
    System.out.println(new BigInteger(sig).toString(16));
    PublicKey pk = t.getPublicKey();
    byte[] pkenc = pk.getEncoded();
    for (int i = 0; i < pkenc.length; i++) {
      System.out.print(pkenc[i] + ",");
    }
  }
}
