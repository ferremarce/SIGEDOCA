/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catequesis.controller;

import catequesis.clases.MyTree;
import catequesis.fachada.TipoTreeFacade;
import catequesis.modelo.TipoTree;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;

/**
 *
 * @author jmferreira
 */
@Named(value = "TipoTreeController")
@SessionScoped
public class TipoTreeController implements Serializable {

    @Inject
    TipoTreeFacade tipoTreeFacade;
    private TipoTree tipoTree;
    private TreeNode root1;
    private TreeNode selectedNode1;
    private TipoTree nodoSeleccionado;

    /**
     * Creates a new instance of TipoTreeController
     */
    public TipoTreeController() {
    }

    public TipoTreeFacade getTipoTreeFacade() {
        return tipoTreeFacade;
    }

    public void setTipoTreeFacade(TipoTreeFacade tipoTreeFacade) {
        this.tipoTreeFacade = tipoTreeFacade;
    }

    public TipoTree getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    public void setNodoSeleccionado(TipoTree nodoSeleccionado) {
        this.nodoSeleccionado = nodoSeleccionado;
    }

    public void init() {
        MyTree t = new MyTree();
        root1 = t.crearArbol(tipoTreeFacade.findAll());
        root1.setExpanded(true);
//        for (TreeNode n : root.getChildren()) {
//            n.setExpanded(true);
//            for (TreeNode n1: n.getChildren()){
//                n1.setExpanded(true);
//            }
//        }
    }

    public TreeNode getRoot1() {
        return root1;
    }

    public void setRoot1(TreeNode root1) {
        this.root1 = root1;
    }

    public TreeNode getSelectedNode1() {
        return selectedNode1;
    }

    public void setSelectedNode1(TreeNode selectedNode1) {
        this.selectedNode1 = selectedNode1;
    }

    public TipoTree getTipoTree() {
        return tipoTree;
    }

    public void setTipoTree(TipoTree tipoTree) {
        this.tipoTree = tipoTree;
    }

    public String doTipoTreeForm() {
        this.init();
        return "/pages/TreeCRUD";
    }

    public void onNodeSelect(NodeSelectEvent event) {
        this.nodoSeleccionado = (TipoTree) selectedNode1.getData();
        System.out.println("click en " + this.nodoSeleccionado.getId()+"-"+this.nodoSeleccionado.getDescripcion());
    }
}
