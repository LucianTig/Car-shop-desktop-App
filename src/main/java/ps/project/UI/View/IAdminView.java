package ps.project.UI.View;


import ps.project.Models.Car;

import javax.swing.table.DefaultTableModel;
import java.util.List;

interface IAdminDataProvider {

    String getUsernameText();
    String getPasswordText();
    String getNameText();
    String getAccountCreationText();
    String getWalletAmountText();

    String getTypeText();
    String getBrandText();
    String getModelText();
    String getPriceText();
    String getStockText();
    String getSoldNumberText();

    String getTableOption();
    String getUpdateColumnCarOption();
    String getUpdateSetColumnCarOption();
    String getDeleteColumnCarOption();

    String getUpdateColumnRegularUserOption();
    String getUpdateSetColumnRegularUserOption();
    String getDeleteColumnRegularUserOption();

    String getTextUpdateValueColumn();
    String getTextUpdateSetValueColumn();
    String getTextDeleteColumnValue();


    String getFileFormat();

    String getChooseSavePath();

}

interface IAdminProvider {

    void showErrorMessage(String message);

    void setCarViewCreate();

    void setRegularUserViewCreate();

    void showCarTable(DefaultTableModel model);

    void showCarList(List<Car> carList);

    void showRegularUserTable(DefaultTableModel model);

    void showChooseFileDialog();

    void accountInformation(String name);

}

public interface IAdminView extends IAdminDataProvider, IAdminProvider {
}
