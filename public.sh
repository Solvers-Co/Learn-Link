#!/bin/bash

# Função para verificar a versão do Node.js
check_node() {
    if type -p node; then
        echo "Node.js encontrado."
        node_version=$(node -v | sed 's/v//')
        if [[ "$node_version" == 18* ]]; then
            echo "Node.js versão 18 já está instalado."
        else
            echo "Versão diferente de Node.js instalada ($node_version)."
            install_node
        fi
    else
        echo "Node.js não encontrado."
        install_node
    fi
}

# Função para instalar o Node.js versão 18
install_node() {
    echo "Instalando Node.js versão 18..."
    sudo apt --fix-broken install -y
    curl -fsSL https://nodejs.org/dist/v22.11.0/node-v22.11.0-linux-x64.tar.xz| sudo -E bash -
    sudo apt install -y nodejs
}

check_node

REPO_URL="https://github.com/Solvers-Co/Learn-Link.git"
REPO_DIR="frontend"


if ! command -v git &> /dev/null; then
    echo "Git não está instalado. Instalando o Git..."
    sudo apt update
    sudo apt install git -y
else
    echo "Git já está instalado."
fi


if [ ! -d "$REPO_DIR" ]; then
    echo "Repositório não encontrado. Clonando o repositório..."
    git clone "$REPO_URL"
else
    echo "Repositório já existe. Atualizando com git pull..."
    cd "$REPO_DIR"
    git pull
fi

echo "Executando o frontend..."
npm start