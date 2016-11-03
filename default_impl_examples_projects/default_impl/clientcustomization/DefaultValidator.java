//$Id:
import javax.swing.*;
import com.adventnet.nms.client.ValidateCredentials;
public class DefaultValidator implements ValidateCredentials
{
    public boolean validate(String userName,String oldPassword, String newPassword)
    {
        System.err.println(" The details:: " + userName + " old pass " + oldPassword + " new " + newPassword  );//No I18N
        if(!isAlphaNumeric(newPassword))
        {
            JOptionPane.showMessageDialog(null,"Password Should be Combination of Alpha-Numeric-Special characters","Change Password",JOptionPane.ERROR_MESSAGE);//No I18N
            return false;
        }
        return true;
    }
    private boolean isAlphaNumeric(String str)
    {
        boolean blnNumeric = false;
        boolean blnAlpha = false;
        
        char chr[] = null;
        if(str != null)
            chr = str.toCharArray();
        
        for(int i=0; i<chr.length; i++)
        {
            if(chr[i] >= '0' && chr[i] <= '9')
                {
                    blnNumeric = true;
                    break;
                }
        }
        
        for(int i=0; i<chr.length; i++)
            {
                if((chr[i] >= 'A' && chr[i] <= 'Z') || (chr[i] >= 'a' && chr[i] <= 'z'))
                    {
                        blnAlpha = true;
                        break;
                    }
            }
        return (blnNumeric && blnAlpha);
    }
}

