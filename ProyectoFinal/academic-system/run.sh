#!/usr/bin/env bash
set -e

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
CYAN='\033[0;36m'
NC='\033[0m'

echo -e "${CYAN}╔══════════════════════════════════════════════════╗${NC}"
echo -e "${CYAN}║   SISTEMA DE GESTIÓN ACADÉMICA - LAUNCHER      ║${NC}"
echo -e "${CYAN}╚══════════════════════════════════════════════════╝${NC}"
echo ""

check_command() {
    if ! command -v "$1" &> /dev/null; then
        echo -e "${RED}[✗] $1 no encontrado${NC}"
        return 1
    fi
    echo -e "${GREEN}[✓] $1 encontrado${NC}"
    return 0
}

echo -e "${YELLOW}[1/4] Verificando dependencias...${NC}"
HAS_DOCKER=false; check_command docker && HAS_DOCKER=true
HAS_JAVA=false; check_command java && HAS_JAVA=true
HAS_MVN=false; check_command mvn && HAS_MVN=true
echo ""

if [ "$HAS_DOCKER" = true ]; then
    echo -e "${YELLOW}[2/4] Levantando base de datos PostgreSQL...${NC}"

    CONTAINER_NAME="postgres_academico"

    if docker ps --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
        echo -e "${GREEN}[✓] Contenedor ${CONTAINER_NAME} ya está corriendo${NC}"
    else
        docker rm -f "$CONTAINER_NAME" 2>/dev/null || true
        docker run -d \
            --name "$CONTAINER_NAME" \
            -e POSTGRES_USER=admin_super \
            -e POSTGRES_PASSWORD=SuperPassword2026* \
            -e POSTGRES_DB=db_sistema_academico \
            -p 5432:5432 \
            -v "$SCRIPT_DIR/db_academico/init-scripts:/docker-entrypoint-initdb.d" \
            postgres:15-alpine

        echo -e "${GREEN}[✓] Iniciando PostgreSQL...${NC}"

        for i in $(seq 1 30); do
            sleep 1
            if docker logs "$CONTAINER_NAME" 2>&1 | grep -q "database system is ready to accept connections"; then
                echo -e "${GREEN}[✓] PostgreSQL listo (esperando init scripts)${NC}"
                sleep 3
                break
            fi
            if [ "$i" -eq 30 ]; then
                echo -e "${YELLOW}[!] Tiempo de espera excedido, revisa: docker logs $CONTAINER_NAME${NC}"
            fi
        done
    fi
else
    echo -e "${YELLOW}[!] Docker no disponible. Asegúrate de tener PostgreSQL en localhost:5432${NC}"
fi
echo ""

if [ "$HAS_MVN" = true ]; then
    echo -e "${YELLOW}[3/4] Compilando proyecto...${NC}"
    cd "$SCRIPT_DIR/app"
    mvn clean compile -q
    echo -e "${GREEN}[✓] Compilación exitosa${NC}"
    cd "$SCRIPT_DIR"
    echo ""

    echo -e "${YELLOW}[4/4] Ejecutando aplicación...${NC}"
    cd "$SCRIPT_DIR/app"
    mvn javafx:run
elif [ "$HAS_JAVA" = true ]; then
    echo -e "${YELLOW}[3/4] Compilando y ejecutando con java directo...${NC}"
    cd "$SCRIPT_DIR/app"
    mvnw compile javafx:run 2>/dev/null || \
    echo -e "${RED}[✗] No se puede ejecutar. Instala Maven: sudo apt install maven${NC}"
else
    echo -e "${RED}[✗] Java no instalado. Ejecuta:${NC}"
    echo -e "  sudo apt update && sudo apt install openjdk-17-jdk maven -y"
    exit 1
fi