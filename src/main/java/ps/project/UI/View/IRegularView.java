package ps.project.UI.View;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

interface IRegularDataProvider {

    String getUsername();

    String getPassword();

    String getSelectedRow();

    int getPriceOption();

    int getTypeOption();

    String getSelectedRowFavoriteCarList();

    String getSelectedRowRateOptionList();

    String getSelectedRowComment();

    String getWriteComment();

    ArrayList<String> getConfigureOprionList();


}

interface IRegularProvider {

    void showErrorMessage(String message);

    void showCarTable(DefaultTableModel model);

    void accountInformation(String name, int walletAmount);

    void showFavoriteCarList(DefaultTableModel model);

    void showComments(DefaultTableModel model);

    void showCart(String carInfo);



}

public interface IRegularView extends IRegularDataProvider, IRegularProvider {
}