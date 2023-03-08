package com.github.bragagustavo.vo;

import java.time.LocalDate;

public class SellReportVo {

    private String productName;
    private Long ammountSold;
    private LocalDate lastSellDate;

    public SellReportVo(String productName, Long ammountSold, LocalDate lastSellDate) {
        this.productName = productName;
        this.ammountSold = ammountSold;
        this.lastSellDate = lastSellDate;
    }

    @Override
    public String toString() {
        return " \n" +
                "Relatório de vendas " + '\n' +
                "Nome do produto:  " + productName + "\n" +
                "Quantidade vendida: " + ammountSold + "\n" +
                "Data da ultimda venda " + lastSellDate + "\n";

    }
}
