/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.clases;

import catequesis.modelo.TipoTree;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author jmferreira
 */
public class MyTree {

    private List<TipoTree> subList2;
    private List<TipoTree> listaPadres;
    private List<TipoTree> listaCompleta;
    private TreeNode root;
    private TreeNode hijos;

    public MyTree() {
    }

    public TreeNode crearArbol(List<TipoTree> listaClasif) {
        this.listaCompleta = listaClasif;
        root = new DefaultTreeNode("Raiz", null);
        //hijos = new DefaultTreeNode("Inicio", root);
        recursive(listaClasif, 0, root);
        //hijos.setExpanded(true);
        return root;
    }

    public void recursive(List<TipoTree> listaClasif, Integer id, TreeNode node) {
        subList2 = new ArrayList<>();
        subList2 = getRegistroByPadre(id);

        for (TipoTree k : subList2) {
            TreeNode childNode = new DefaultTreeNode(k, node);

            recursive(subList2, k.getId(), childNode);
        }
    }

    public List<TipoTree> getRegistroByPadre(Integer i) {
        listaPadres = new ArrayList<>();
        try {
            for (TipoTree k : this.listaCompleta) {
                if (i == 0 && k.getIdPadre() == null) {
                    listaPadres.add(k);
                } else {
                    if (k.getIdPadre()!=null && k.getIdPadre().getId().compareTo(i) == 0) {
                        listaPadres.add(k);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaPadres;
    }
}
