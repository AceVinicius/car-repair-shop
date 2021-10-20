package Main;

import Model.Address;
import Model.Catalog;
import Model.Category;
import Model.City;
import Model.Client;
import Model.EItemType;
import Model.Employee;
import Model.Item;
import Model.Model;
import Model.ServiceOrder;
import Model.Vehicle;

public class App {
    public static void main(final String[] args) throws Exception {
        City rioDeJaneiro = new City("Rio de Janeiro", "RJ");
        City petropolis = new City("Petrópolis", "RJ");
        City saoPaulo = new City("São Paulo", "SP");
        City niteroi = new City("Niterói", "RJ");

        System.out.println("\n----------( class: City - rioDeJaneiro )----------\n");
        System.out.println("Nome: " + rioDeJaneiro.getName());

        System.out.println("\n----------( class: City - petropolis )----------\n");
        System.out.println("Nome: " + petropolis.getName());

        System.out.println("\n----------( class: City - niteroi )----------\n");
        System.out.println("Nome: " + niteroi.getName());

        System.out.println("\n----------( class: City - saoPaulo )----------\n");
        System.out.println("Nome: " + saoPaulo.getName());

        Address casa = new Address("Rua Jorge Emilio Fontenele", 110, "Recreio dos Bandeirantes", rioDeJaneiro);
        Address trabalho = new Address("R. Alice Hervé", 356, "Bingen", petropolis);

        Client luizEduardo = new Client(1, "Luiz Eduardo", 1234, casa);
        Client viniciusAguiar = new Client(2, "Vinicius Aguiar", 1243, trabalho);

        viniciusAguiar.setPlatinum(true);

        Employee joaoVictor = new Employee(3, "João Victor", 1324, casa);

        joaoVictor.setEmail("aulaDe...@iCaiu.no.br");

        Model aventador = new Model("Aventador");
        Model murcielago = new Model("Murciélago");

        System.out.println("\n----------( class: Model - aventador )----------\n");
        System.out.println("Model: " + aventador.getName());

        System.out.println("\n----------( class: Model - murcielago )----------\n");
        System.out.println("Model: " + murcielago.getName());

        Vehicle lamborghini_1 = new Vehicle(viniciusAguiar, aventador, "SN-20140001-BR", 2014, "Laranja");
        Vehicle lamborghini_2 = new Vehicle(luizEduardo, murcielago, "SN-20090013-BR", 2009, "Amarelo");

        lamborghini_1.setPlate("LPA-4552");
        lamborghini_2.setPlate("LSF-4532");

        Item maoDeObra = new Item(EItemType.SERVICE, "Mão de Obra");
        Item item_1 = new Item(EItemType.PART, "Roda (individual)");
        Item item_2 = new Item(EItemType.PART, "Pneu (kit com 4)");
        Item item_3 = new Item(EItemType.SERVICE, "Balanceamento");
        Item item_4 = new Item(EItemType.PART, "Pintura");
        Item item_5 = new Item(EItemType.SERVICE, "Lavagem");

        System.out.println("\n----------( class: Item - item_1 )----------\n");
        System.out.println(item_1.listItem());

        System.out.println("\n----------( class: Item - item_2 )----------\n");
        System.out.println(item_2.listItem());

        System.out.println("\n----------( class: Item - item_3 )----------\n");
        System.out.println(item_3.listItem());

        System.out.println("\n----------( class: Item - item_4 )----------\n");
        System.out.println(item_4.listItem());

        System.out.println("\n----------( class: Item - item_5 )----------\n");
        System.out.println(item_5.listItem());

        Category pecas = new Category("Peças");
        Category servicos = new Category("Serviços");
        Category mdb = new Category("Mão de Obra");

        pecas.addItem(item_1);
        pecas.addItem(item_2);
        pecas.addItem(item_4);
        servicos.addItem(item_3);
        servicos.addItem(item_5);
        mdb.addItem(maoDeObra);

        System.out.println("\n----------( class: Category - pecas )----------\n");
        System.out.println("Nome: " + pecas.getNome());

        System.out.println("\n----------( class: Category - servicos )----------\n");
        System.out.println("Nome: " + servicos.getNome());

        Catalog catalogo = new Catalog();

        catalogo.addCategory(pecas);
        catalogo.addCategory(servicos);
        catalogo.addCategory(mdb);

        ServiceOrder ordem_1 = new ServiceOrder(lamborghini_1, 25987);
        lamborghini_1.addService(ordem_1);

        ServiceOrder ordem_2 = new ServiceOrder(lamborghini_2, 1234567);
        lamborghini_2.addService(ordem_2);

        ordem_1.setDescription("Consertar rodas frontais com defeito e balancear. Lavar o carro no final.");
        ordem_1.addItem(maoDeObra, 1);
        ordem_1.addItem(item_1, 2, 1500);
        ordem_1.addItem(item_3, 4);
        ordem_1.addItem(item_5, 1, 1000);

        ordem_2.setDescription("Trocar rodar e pintar o carro de azul. Lavar o carro no final.");
        ordem_2.addItem(maoDeObra, 1);
        ordem_2.addItem(item_2, 1);
        ordem_2.addItem(item_4, 1);
        ordem_2.addItem(item_5, 1);

        System.out.println("\n----------( class: ServiceOrder - ordem_1 )----------\n");
        System.out.println(ordem_1.listServiceOrder());

        System.out.println("\n----------( class: ServiceOrder - ordem_2 )----------\n");
        System.out.println(ordem_2.listServiceOrder());

        System.out.println("\n----------( class: Vehicle - lamborghini_1 )----------\n");
        System.out.println(lamborghini_1.listServices());

        System.out.println("\n----------( class: Vehicle - lamborghini_2 )----------\n");
        System.out.println(lamborghini_2.listServices());

        System.out.println("\n");
    }
}
