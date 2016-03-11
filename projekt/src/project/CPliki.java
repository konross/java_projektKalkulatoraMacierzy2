/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *Klasa do wczytywania plików
 * 
 * @author Konrad Stano i Paweł Szostak
 */
public class CPliki {
    
    protected String sciezka;
    protected int n;
    protected int i=0;
    protected String tablica[];
    protected File plik;
    
    /**
     * Metoda dzięki której wyświetla nam się okienko do przeszukiwania pamięci
     * w celu znalezienia pliku do wczytania.
     * 
     * @param text
     * @throws FileNotFoundException 
     */
    void Szukaj(JTextField text) throws FileNotFoundException
    {
        JFileChooser wybierz = new JFileChooser();
        if(wybierz.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            plik = wybierz.getSelectedFile();
            sciezka=plik.getAbsolutePath();
            text.setText(sciezka);
        }
    }
    
    /**
     * Tu dokonuje się wczytanie danych do tabelki jTable1.
     * 
     * @param tab
     * @param mod
     * @param text
     * @return
     * @throws FileNotFoundException 
     */
    int Wczytaj(JTable tab, DefaultTableModel mod, JTextField text) throws FileNotFoundException 
    {
        sciezka=text.getText();
        if(sciezka.equals("Wybierz plik"))
        {
            showMessageDialog(null,"Nie wybrałeś pliku!");
            return 0;
        }
        plik=new File(sciezka);
        
        Scanner skaner = new Scanner(plik);
            try
            {
                n = Integer.parseInt(skaner.nextLine());
            }
            catch(NumberFormatException e) 
            { 
                showMessageDialog(null,"Zmienna n w pliku nie jest liczbą! Popraw to!");
                return 0;
            }
            if(n<0 && n==(int)n)
            {
                showMessageDialog(null, "Zmienna n w pliku jest ujemna! Popraw to!");
                return 0;
            }
            tablica =new String[n];
            while(skaner.hasNext())
            {
                tablica[i]=skaner.nextLine();
                i++;
            }
        
        
        mod.setRowCount(n);
        for(int i=0; i<n; i++)
        {
            int g=i+1;
            tab.setValueAt(g, i, 0);
            tab.setValueAt(tablica[i], i, 1);
        }
        return 0;
    }
    
    /**
     * Ta metoda zwraca nam ilość równań i niewiadomych w przypadku, gdy
     * chcmy wczytać dane z pliku.
     * 
     * @return 
     */
    int ilosc()
    {
        return n;
    }
            
}
