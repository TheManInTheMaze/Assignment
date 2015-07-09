package service;

import model.interfaces.IGrid;

/**
 * Created by pcorentin on 09/07/2015.
 */
public class ResponseWrapper {
    private IGrid grid;
    private String responceCode;


    public ResponseWrapper(IGrid grid, String responceCode) {
        this.grid = grid;
        this.responceCode = responceCode;
    }

    public String getResponceCode() {
        return responceCode;
    }

    public void setResponceCode(String responceCode) {
        this.responceCode = responceCode;
    }

    public IGrid getGrid() {
        return grid;
    }

    public void setGrid(IGrid grid) {
        this.grid = grid;
    }
}
