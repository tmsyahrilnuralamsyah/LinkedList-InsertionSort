import java.util.*;
import java.io.*;

public class LinkedListSort {
    private Node head;
    private int size;

    public LinkedListSort() {
        this.head = null;
        this.size = 0;
    }

    public void setHead(Node head) {
        this.head = head;
        this.incSize();
    }

    public void incSize() {
        this.size++;
    }
    
    public void decSize() {
        this.size--;
    }

    public int Size() {
        return this.size;
    }

    public Node getHead() {
        return this.head;
    }

    public Node searchData(String nama) {
        Node current = this.getHead();

        while (current != null) {
            if (current.getData().getNama().toLowerCase().equals(nama.toLowerCase())) {
                return current;
            }
            if (current.getNext() == null) {
                break;
            }
            current = current.getNext();
        }
        return null;
    }

    public String fetchData(String nama) {
        Node isFound = this.searchData(nama);

        if (isFound != null) {
            System.out.print("\n=> ");
            return isFound.getData().getNama() + " mempunyai " + isFound.getData().getFreq() + " artikel.";
        } else {
            return "\nData dengan nama " + nama + " tidak ditemukan.";
        }
    }

    public void fetchAllData(String nama) {
        Node isFound = this.searchData(nama);
        if (isFound != null) {
            System.out.println(this.fetchData(nama));
            isFound.getData().getJudul();
        } else {
            System.out.println("\nData dengan nama " + nama + " tidak ditemukan.");
        }
    }

    public void add(String nama) {
        Node isFound = this.searchData(nama);

        if (isFound == null) {
            this.setHead(new Node(new Data(nama), this.getHead()));
        } else {
            isFound.getData().addFreq();
        }
    }

    public void searchContainsEditor(String nama) {
        Node current = this.getHead();

        System.out.println();
        while (current != null) {
            if (current.getData().getNama().toLowerCase().contains(nama.toLowerCase())) {
                System.out.println("-| " + current.getData().getNama() + " ditemukan dengan " + current.getData().getFreq() + " artikel");
            }

            if (current.getNext() == null) {
                break;
            }
            current = current.getNext();
        }
    }

    public void insertionSort() {
        Node current = this.getHead();
        Data temp;

        while (current.getNext() != null) {
            while (current.getData().getNama().compareTo(current.getNext().getData().getNama()) > 0) {
                temp = current.getNext().getData();
                current.getNext().setData(current.getData());
                current.setData(temp);
                current = this.getHead();
            }
            current = current.getNext();
        }
    }

    public void display() {
        Node current = this.getHead();
        int x = 1;

        System.out.println();
        while (current != null) {
            System.out.println(x + ". " + current.getData().getNama() + ", mempunyai " + current.getData().getFreq() + " artikel");
            if (current.getNext() == null) {
                break;
            }
            current = current.getNext();
            x++;
        }
    }

    public static void main(String[] args) {
        LinkedListSort ll = new LinkedListSort();
        Scanner scan = new Scanner(System.in);

        try {
            File myObj = new File("sda lab.txt");
            Scanner baca = new Scanner(myObj);
            while (baca.hasNextLine()) {
                String data = baca.nextLine();
                String[] splitter = data.split("::");
                ll.add(splitter[0]);

                if(ll.searchData(splitter[0]) != null){
                    ll.searchData(splitter[0]).getData().addJudul(splitter[1]);
                }
            }
            baca.close();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan");
            e.printStackTrace();
        }

        ll.insertionSort();
        System.out.println("\n[=]==============   Data Artikel   ==============[=]");
        while (true) {
            System.out.println("\n[=]----------------------------------------------[=]");
            System.out.println(" |                      Menu                      |");
            System.out.println("[=]----------------------------------------------[=]");
            System.out.println(" | 1. Tampilkan semua nama pemilik artikel        |");
            System.out.println(" | 2. Cari artikel dengan nama                    |");
            System.out.println(" | 3. Cari nama pemilik artikel                   |");
            System.out.println(" | 4. Exit                                        |");
            System.out.println("[=]----------------------------------------------[=]");
            System.out.print("\nMasukkan Pilihan : ");
            int pilihan = Integer.parseInt(scan.nextLine());
            if (pilihan == 1) {
                ll.display();
            } else if (pilihan == 2) {
                System.out.print("Masukkan nama lengkap nama : ");
                String nama = scan.nextLine();
                ll.fetchAllData(nama);
            } else if (pilihan == 3) {
                System.out.print("Masukkan nama : ");
                String nama = scan.nextLine();
                ll.searchContainsEditor(nama);
            } else if (pilihan == 4) {
                System.out.println("--------------------");
                System.exit(1);
            } else {
                System.out.println("\nPilihan tidak ditemukan");
            }
        }
    }
}

class Node {
    private Data data;
    private Node next;

    public Node(Data data, Node next) {
        this.setNext(next);
        this.setData(data);
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public Node getNext() {
        return this.next;
    }
}

class Data {
    private String nama;
    private LinkedListSort judul = new LinkedListSort();
    private int freq;

    public Data(String nama) {
        this.nama = nama;
        this.freq = 1;
    }

    public String getNama() {
        return this.nama;
    }

    public int getFreq() {
        return this.freq;
    }

    public void addFreq() {
        this.freq += 1;
    }

    public void addJudul(String artikel) {
        judul.add(artikel);
    }

    public void getJudul() {
        Node current = judul.getHead();
        int i = 1;

        System.out.println("   Judul-judul artikelnya :");
        while(current != null){
            System.out.println(i+". "+current.getData().getNama());
            if(current.getNext() == null){
                break;
            }
            current = current.getNext();
            i++;
        }
    }
}