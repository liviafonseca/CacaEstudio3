package br.com.pos.cacaestudio.helper;


import java.util.ArrayList;
import java.util.List;

import br.com.pos.cacaestudio.modelo.entity.Estudio;

/**
 * Created by livia on 14/03/2018.
 */

public class EstudioHelper {



    //o método faz 'as vezes' de um preenchimento através de um webService
    public List<Estudio> gerarListaDeEstudios(){

        List<Estudio> lista = new ArrayList<>();
        Estudio estudio = new Estudio();

        estudio.setNome("Marubá");
        estudio.setEndereco("Conjunto Castelo Branco, 24 - Parque Dez de Novembro, Manaus - AM, 69055-420");
        estudio.setTelefone("(92) 98192-0919");
        estudio.setPreco(40);
        estudio.setMedia(0);
        estudio.setImg("https://lh5.googleusercontent.com/p/AF1QipPOqAOUm4KFY0B57pXcs_M6EE7PriOTgbBavajL=w90-h90-n-k-no");
        lista.add(estudio);

        estudio = new Estudio();
        estudio.setNome("E.S.P.");
        estudio.setEndereco("R. A 5, 1061 - Japiim Manaus, Manaus - AM, 69076-050");
        estudio.setTelefone("(92) 98214-1201");
        estudio.setPreco(45);
        estudio.setMedia(0);
        estudio.setImg("https://lh5.googleusercontent.com/p/AF1QipNsQciscg3frWWhVYQr-qkJzzqzR02KgWK_-wch=w90-h90-n-k-no");
        lista.add(estudio);

        estudio = new Estudio();
        estudio.setNome("Atrium");
        estudio.setEndereco("Rua Manoel Borba Gato, 18 - Alvorada, Manaus - AM, 69042-500");
        estudio.setTelefone("(92) 3658-1633");
        estudio.setPreco(50);
        estudio.setMedia(0);
        estudio.setImg("");
        lista.add(estudio);

        estudio = new Estudio();
        estudio.setNome("XP");
        estudio.setEndereco("Promissão, 369, R. Padre Luiz Ruas - Raiz, Manaus - AM, 69068-380");
        estudio.setTelefone("(92) 99162-7386");
        estudio.setPreco(25);
        estudio.setMedia(0);        estudio.setImg("https://lh5.googleusercontent.com/p/AF1QipND_hyK0YCx8f0XSV9xgTWPwHxT1BulATWOdiR-=w90-h90-n-k-no");
        lista.add(estudio);

        estudio = new Estudio();
        estudio.setNome("Jota Music");
        estudio.setEndereco("R. 1, 21 - Jorge Teixeira, Manaus - AM");
        estudio.setTelefone("(92) 99372-7849");
        estudio.setPreco(45);
        estudio.setMedia(4.5);
        estudio.setImg("https://lh5.googleusercontent.com/p/AF1QipNNJcDskrF2Xl8HdGeSEHgb8d2M8F5jsaHnoTAv=w90-h90-n-k-no");
        lista.add(estudio);

        estudio = new Estudio();
        estudio.setNome("LS Brazil");
        estudio.setEndereco("Rua A 13 (Raul De LEONY) N.1170 2 Entrando na Evangelica - Japiim 2, Manaus - AM, 69076-130");
        estudio.setTelefone("(92) 98193-8675");
        estudio.setPreco(60);
        estudio.setMedia(5.3);
        estudio.setImg("https://lh5.googleusercontent.com/p/AF1QipN-QGMm1GUBkRQyQJKC2LY7FPlK0hjC5bQucpD1=w90-h90-n-k-no");
        lista.add(estudio);

        estudio = new Estudio();
        estudio.setNome("Supersônico");
        estudio.setEndereco("R. um, 25 - Dom Pedro, Manaus - AM, 69040-080");
        estudio.setTelefone("(92) 91234-5625");
        estudio.setPreco(35);
        estudio.setMedia(4.8);
        estudio.setImg("https://lh5.googleusercontent.com/p/AF1QipNV76GYSeGJcKGxnGxSO2nvzQg24D91l3tJ5qZq=w90-h90-n-k-no");
        lista.add(estudio);

        estudio = new Estudio();
        estudio.setNome("Rota 55");
        estudio.setEndereco(" R. I, 32A - Compensa, Manaus - AM, 69035-736");
        estudio.setTelefone("(92) 99228-5624");
        estudio.setPreco(50);
        estudio.setMedia(4.7);
        estudio.setImg("https://lh5.googleusercontent.com/p/AF1QipOtQzdfMsSFSOVzSbx2tpxH8pe5zEbO47KJ78Is=w90-h90-n-k-no");
        lista.add(estudio);

        estudio = new Estudio();
        estudio.setNome("Amazon Music Studio");
        estudio.setEndereco("R. São Pedro, 50 - Petrópolis, Manaus - AM, 69079-420");
        estudio.setTelefone("(92)98751-5687");
        estudio.setPreco(45);
        estudio.setMedia(4.8);
        estudio.setImg("https://lh5.googleusercontent.com/p/AF1QipNdJbVgzrRhD_w1XXBMCGJh2jsbq31PesASnBSq=w90-h90-n-k-no");
        lista.add(estudio);

        estudio = new Estudio();
        estudio.setNome("John Music Studio");
        estudio.setEndereco("R. do Castanho, 76 - São José Operário, Manaus - AM, 69085-110");
        estudio.setTelefone("(92) 99119-9730");
        estudio.setPreco(60);
        estudio.setMedia(5.0);
        estudio.setImg("https://lh5.googleusercontent.com/p/AF1QipM-D50W2LIp0NAYODN3qaI8qOk7Y9C0ageqlnpe=w90-h90-n-k-no");
        lista.add(estudio);

        return lista;
    }
}
