
package activityJava.activityJava;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Zahran Rafif Pc
 */
@Controller
public class RequestController {
    @RequestMapping("/sayuran")
    public String Input (HttpServletRequest data, Model model)
    {     
        String inSayur = data.getParameter("sayuran");      
        String inHarga = data.getParameter("hargaperkilo");  
        Integer hargaperkilo = Integer.valueOf(inHarga);
        
        String inJumlahBel = data.getParameter("jumlahygdibeli");
        Double jumlahBeli = Double.valueOf(inJumlahBel);
        
        String inAwal = data.getParameter("pembAwal");
        Double pembayaranawal = Double.valueOf(inAwal);  
        
        Double tothargadiskon = jumlahBeli * hargaperkilo;  
        Double diskon = pembayaranawal - tothargadiskon;   
        
        String keterangan = " ";
        String jum_diskon = " ";

        if (tothargadiskon < 16000)
        {
            diskon = tothargadiskon * 0/100;

            jum_diskon = "0%";
        }
        else if (tothargadiskon > 16000 || tothargadiskon <25000)
        {
            diskon = tothargadiskon * 10/100;

            jum_diskon = "10%";
        }
        else if (tothargadiskon > 25000)
        {
            diskon = tothargadiskon * 15/100;

            jum_diskon = "15%";
        }
        
        Double totalbayar = tothargadiskon - diskon;    
        Double kembalian = pembayaranawal - totalbayar;       
        Double kurang =  totalbayar - pembayaranawal;
        
        if (pembayaranawal < totalbayar)
        {
            keterangan = "Uangnya kurang  : " + kurang;
        }
        else if (pembayaranawal > totalbayar)
        {
            keterangan = "Uang Kembalian Anda : " + kembalian;
        }
        else
        {
            keterangan = "Uang Anda benar";  
        }
        
        model.addAttribute("Sayuran", inSayur);
        model.addAttribute("Harga", inHarga );
        model.addAttribute("Jumlah", inJumlahBel);
        model.addAttribute("Total", totalbayar);
        model.addAttribute("jumDiscount", tothargadiskon);
        model.addAttribute("disc", diskon);
        model.addAttribute("Pembayaranawal",inAwal);
        model.addAttribute("jumDiskon",jum_diskon);
        model.addAttribute("ket",keterangan);
        
        return "View2";
    }   
}
