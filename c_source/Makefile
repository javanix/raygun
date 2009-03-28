COMPILER = gcc
CCFLAGS  = -g -std=c99
PKG_CFG  = $(shell pkg-config --cflags --libs libavformat libavcodec libavdevice libswscale libavutil libpostproc)
FIN_FLAGS = -lm -lz -lfaac -lx264 -lfaad -lmp3lame -ltheora -lxvidcore

all:
	$(COMPILER) $(CCFLAGS) ffanalyze.c $(PKG_CFG) $(FIN_FLAGS) -o ffanalyze

clean: 
	rm -rf *.o *.out ffanalyze
