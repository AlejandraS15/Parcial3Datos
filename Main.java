public class Main {

    // Para la imagen que desea procesar, ingrese la matriz que representa dicha imagen aqui
    static int[][] imagen = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 255, 255, 0, 0, 0, 0, 255, 0, 0},
            {0, 255, 255, 255, 255, 0, 0, 255, 0, 0},
            {0, 255, 255, 255, 0, 0, 0, 255, 0, 0},
            {0, 0, 255, 255, 0, 0, 255, 255, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 255, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 255, 255, 255, 255, 255, 0, 0},
            {0, 0, 0, 0, 255, 255, 255, 255, 0, 0},
            {0, 0, 0, 0, 0, 255, 0, 0, 0, 0}
    };
    // Defino mi matriz visitados de boleanos, el numero de filas y columnas, para utilizar estas variables globales en todas las funciones.

    static boolean[][] visitados;
    static int filas, columnas;

    public static void main(String[] args) {
        filas = imagen.length;
        columnas = imagen[0].length;
        //Creo la matriz visitados del mismo tamaño de la matriz que procesare
        visitados = new boolean[filas][columnas];

        int numObjetos = numObjetos();
        System.out.println("Se encontraron en la imagen: " + numObjetos+" objetos");
    }

    public static int numObjetos() {
        int numObjetosEncontrados = 0;
        //Recorro mi matriz
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // Si encontramos un píxel de objeto (menor a 255) no visitado, iniciamos la busqueda DFS
                if (imagen[i][j] < 255 && !visitados[i][j]) {
                    dfs(i, j);
                    numObjetosEncontrados++; // Contamos el objeto encontrado
                }
            }
        }
        return numObjetosEncontrados;
    }

    // Implementamos esta funcion, que implementa la busqueda DFS para explorar los píxeles conectados
    public static void dfs(int x, int y) { //Tiene como parametro la coordenada del pixel
        // Al momento de ingresar a esta funcion, marcamos ese pixel en la coordenada ingresada x,y como visitado.
        visitados[x][y] = true;

        // Direcciones posibles (vertical, horizontal y diagonal)
        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
        //Recorre las 8 diferentes posiciones desde las coorenadas ingresadas
        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // Llamamos a la funcion validar que comprueba si la nueva posición es válida y forma parte del objeto
            if (validar(nx, ny)) {
                //Llamamos recursivamente a la funcion dfs para continuar la busqueda de mas pixeles conectados.
                dfs(nx, ny);
            }
        }
    }

    // Verifica si el píxel es válido y es parte del objeto
    public static boolean validar(int x, int y) {
        boolean valido = false;
        //Con esta funcion validamos que el pixel este dentro de la matriz, que el pixel no sea fondo y que el pixel no se halla visitado.
        if(x >= 0 && x < filas && y >= 0 && y < columnas && imagen[x][y] < 255 && !visitados[x][y]){
            valido = true;
        }
        return valido;
    }
}
