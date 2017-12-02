package UpdateTables;

import Database.Part;
import javafx.scene.control.TextArea;

import java.util.List;

/**
 * Created by colto on 2017-12-01.
 */
public class UpdateError
{
    public static void updateError(TextArea area)
    {
        List list = UpdateInventory.getArray();
        boolean changed = false;

        for(int i = 0; i < list.size(); i++)
        {
            Part part = (Part) list.get(i);
            if(part.getNumOnHand() < 4)
            {
                changed = true;
                area.appendText(part.getPartNumber() +" is running low. Only " +part.getNumOnHand() +" remaining.\n");
            }
        }

        if(!changed)
        {
            area.clear();
        }
    }
}
