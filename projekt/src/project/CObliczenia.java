/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import static java.lang.StrictMath.abs;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *Klasa do obliczeń
 * 
 * @author Konrad Stano i Paweł Szostak
 */
public class CObliczenia {
    public int n;
    protected String tablica[];
    protected String[] parts;
    protected double[][] macierz;
    protected int[] wektorkol;
    protected double[] wynik;
    protected double eps=0.000000000001;
    protected double m, s;
    protected int i, j, k, temp;
    

    /**
     *Przekazuje ilość równań i niewiadomych zczytanych z jTextField1 w głównej
     * klasie ss_main
     * 
     * @param ile
     */
    public CObliczenia(int ile) {
        this.n = ile;
    }
   
    /**
     * Główna metoda w której wykonywane są wszystkie obliczenia metodą
     * Gaussa-Crouta
     * 
     * @return 
     */
    int WyznaczWartosci() {
        macierz =new double[n][n+1];
        wektorkol =new int[n+1];
        wynik =new double[n];
        
        //podane w tablece dane rozdzielamy na tablic macierzy i tablice wyrazów wolnych
        for(i=0; i<n; i++)
        {
            parts = tablica[i].split(";");
            for(j=0; j<=n; j++)
            {
                try
                {
                    macierz[i][j]=Integer.parseInt(parts[j]);
                }
                catch(NumberFormatException e) 
                { 
                    showMessageDialog(null,"Błędne dane! Popraw dane w linii: "+(i+1)+" na pozycji nr.: "+(j+1));
                    return 0;
                }   
            }
        }
        
        for(i=0; i<=n; i++)
        {
            wektorkol[i]=i;
        }
        // mam juz uzupelnione dane, teraz liczymy
        
        // eliminacja współczynników

        for( i = 0; i < n - 1; i++)
        {
          k = i;
          for(j = i + 1; j < n; j++)
            if(abs(macierz[i][wektorkol[k]]) < abs(macierz[i][wektorkol[j]])) k = j;
          
          temp=wektorkol[k];
          wektorkol[k]=wektorkol[i];
          wektorkol[i]=temp;
          
          for(j = i + 1; j < n; j++)
          {
            if(abs(macierz[i][wektorkol[i]]) < eps) 
            {
                return 1;
            }
            else
            {
                m = -macierz[j][wektorkol[i]] / macierz[i][wektorkol[i]];
                for(k = i + 1; k <= n; k++)
                {
                  macierz[j][wektorkol[k]] += m * macierz[i][wektorkol[k]];
                }
            }
          }
        }
        
        // wyliczanie niewiadomych

        for(i = n - 1; i >= 0; i--)
        {
          if(abs(macierz[i][wektorkol[i]]) < eps)
          {
              return 1;
          }
          else
          {
            s = macierz[i][n];
            for(j = n - 1; j >= i + 1; j--)
            {
              s -= macierz[i][wektorkol[j]] * wynik[wektorkol[j]];
            }
            wynik[wektorkol[i]] = s / macierz[i][wektorkol[i]];
          }
        }
        return 2;
    }

    /**
     * Metoda dzięki której dane z tablicy jTable1 są przypisywane do tablicy
     * w celu późniejszego ich wykorzystania do obliczeń.
     * 
     * @param tab 
     */
    void PrzypiszDane(JTable tab) {
        tablica =new String[n];
        for(i=0; i<n; i++)
        {
            tablica[i]=tab.getValueAt(i, 1).toString();
        }
    }
 
    /**
     * Metoda dzięki której tworzona jest tablica jTable2 w której wyświetlane
     * są wyniki.
     * 
     * @param tab
     * @param mod 
     */
    void PrzypiszRozw(JTable tab, DefaultTableModel mod) {
        mod.setRowCount(n);
        for(i=0; i<n; i++)
        {
            int g=i+1;
            tab.setValueAt(g, i, 0);
            tab.setValueAt(wynik[i], i, 1);
        }
    }

    /**
     * Metoda dzięki której tworzona jest tablica jTable1 przechowywująca
     * dane do obliczeń.
     * 
     * @param tab
     * @param mod 
     */
    void PokazDane(JTable tab, DefaultTableModel mod) {
        mod.setRowCount(n);
        for(i=0; i<n; i++)
        {
            int g=i+1;
            tab.setValueAt(g, i, 0);
            tab.setValueAt(n, i, 1);
        }
    }
    
}
