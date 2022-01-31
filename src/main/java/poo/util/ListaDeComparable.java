package poo.util;

public class ListaDeComparable<T extends Comparable<T>> {
    int numarCurentDeElemente;
    T[] elemente;
    int dimensiuneMaxima;

    public ListaDeComparable(int dimensiuneMaxima, Class<T> tipulElementelor) {
        numarCurentDeElemente = 0;
        elemente = (T[]) java.lang.reflect.Array.newInstance(tipulElementelor, dimensiuneMaxima);
        this.dimensiuneMaxima = dimensiuneMaxima;
    }

    public void afisareElemente() {
        if (numarCurentDeElemente == 0) {
            System.out.println("Lista este goala");
        } else {
            for (int i = 0; i < numarCurentDeElemente; i++) {
                System.out.print(elemente[i] + " ");
            }
            System.out.println();
        }
    }

    public void adaugareElement(T element) throws ExceptieListaPlina {
        if (numarCurentDeElemente == dimensiuneMaxima) {
            throw new ExceptieListaPlina();
        } else {
            numarCurentDeElemente++;
            if (numarCurentDeElemente > 1) {
                for (int i = numarCurentDeElemente - 1; i > 0; i--) {
                    elemente[i] = elemente[i - 1];
                }
            }
            elemente[0] = element;
            System.out.println("A fost adaugat elementul:" + element);
        }
    }

    public void eliminareElement() throws ExceptieListaGoala {
        if (numarCurentDeElemente == 0)
            throw new ExceptieListaGoala();
        else {
            System.out.println("Am sters elementul: " + elemente[numarCurentDeElemente - 1]);
            elemente[numarCurentDeElemente - 1] = null;
            numarCurentDeElemente--;
        }
    }

    public void sortare() throws ExceptieListaGoala {
        if (numarCurentDeElemente == 0)
            throw new ExceptieListaGoala();
        for (int i = 0; i < numarCurentDeElemente - 1; i++) {
            for (int j = 0; j < numarCurentDeElemente - 1 - i; j++) {
                if (elemente[j].compareTo(elemente[j + 1]) == -1) {
                    T aux = (T) elemente[j];
                    elemente[j] = elemente[j + 1];
                    elemente[j + 1] = aux;
                }
            }
        }
        System.out.print("Lista a fost ordonata descrescator");
    }

    @Override
    public String toString() {
        String strings = "";
        for (int i = 0; i < numarCurentDeElemente; i++) {
            if (i < numarCurentDeElemente - 1) {
                strings = strings + elemente[i].toString() + "\n";
            } else {
                strings = strings + elemente[i].toString();
            }
        }
        return strings;
    }
}
