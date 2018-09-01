    Node *lca(Node *root, int v1,int v2) {
        if ( v1 > v2 ) {
            int tmp = v2;
            v2 = v1;
            v1 = tmp;
        }
        if ( root == NULL) { return NULL;}
        if ( (v2 > root->data && v1 < root->data) || (v1 == root->data || v2 == root->data)) 
        {return root;}
        if ( v2 < root->data) { return lca(root->left, v1, v2);}
        if (v1 > root->data) { return lca(root->right, v1, v2);}
        return root;
    }
