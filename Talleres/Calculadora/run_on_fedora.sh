#!/usr/bin/env bash
set -euo pipefail

# Script para instalar dependencias (Fedora), compilar y ejecutar la GUI de la Calculadora
# Uso: sudo ./run_on_fedora.sh  (requiere permisos para instalar paquetes)

REQUIRED_PACKAGES=(java-17-openjdk-devel xorg-x11-server-Xvfb)

echo "Actualizando repositorios..."
sudo dnf makecache --refresh -y

echo "Instalando paquetes: ${REQUIRED_PACKAGES[*]}"
sudo dnf install -y "${REQUIRED_PACKAGES[@]}"

echo "Verificando java/javac"
java -version
javac -version

echo "Compilando clases Java..."
javac *.java

echo "Ejecutando GUI dentro de Xvfb por 10 segundos de prueba (presiona Ctrl+C para abortar)..."
# Ejecutar en background con xvfb-run durante 10s para verificar que inicia sin mostrar ventana real
xvfb-run -s "-screen 0 1024x768x24" timeout 10s java CalculadoraGUI || true

echo "Si no hubo errores visibles, la GUI se inició correctamente dentro de Xvfb. Para ejecutarla en tu sesión gráfica (Wayland/X11), ejecuta:

  java CalculadoraGUI

Si necesitas ejecutar en Wayland con XWayland, asegúrate de que XWayland esté instalado y activo."