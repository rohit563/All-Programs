/*
    transverse to this folder ./testclient
    gcc main.c -o client
    ./client

 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>

#define BUFSIZE 8192

/*
 * error - wrapper for perror
 */


int main(void) {
    int sockfd, sockfd2, portno, portno2, n;
    int serverlen;
    int sizing;
    int quit = 0;
    struct sockaddr_in serveraddr;
    struct sockaddr_in serveraddr2;
    struct hostent *server;
    struct hostent *server2;
    char *hostname;
    char *hostname2;
    char hawkid[] = "rbanda";
    char q[] = "quit";
    char space[] = " ";
    char game[5];
    char buf[BUFSIZE];
    char buf2[BUFSIZE];
    char buf3[BUFSIZE];
    char buf4[BUFSIZE];


    /* check command line arguments */

    hostname = "c-lnx000.engr.uiowa.edu";
    portno = 23511;

    /* socket: create the socket */
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0)
        error("ERROR opening socket");

    /* gethostbyname: get the server's DNS entry */
    server = gethostbyname(hostname);
    if (server == NULL) {
        fprintf(stderr,"ERROR, no such host as %s\n", hostname);
        exit(0);
    }

    /* build the server's Internet address */
    bzero((char *) &serveraddr, sizeof(serveraddr));
    serveraddr.sin_family = AF_INET;
    bcopy((char *)server->h_addr,
	  (char *)&serveraddr.sin_addr.s_addr, server->h_length);
    serveraddr.sin_port = htons(portno);

    /* connect: create a connection with the server */
    if (connect(sockfd, (struct sockaddr *)&serveraddr, sizeof(serveraddr)) < 0)
      error("ERROR connecting");

      /* UDP Part */

    hostname2 = "c-lnx001.engr.uiowa.edu";
    portno2 = 23510;

    /* socket: create the socket */
    sockfd2 = socket(AF_INET, SOCK_DGRAM, 0);
    if (sockfd2 < 0)
        error("ERROR opening socket");


    /* gethostbyname: get the server's DNS entry */
    server2 = gethostbyname(hostname2);
    if (server2 == NULL) {
        fprintf(stderr,"ERROR, no such host as %s\n", hostname2);
        exit(0);
    }

    /* build the server's Internet address */
    bzero((char *) &serveraddr2, sizeof(serveraddr2));
    serveraddr2.sin_family = AF_INET;
    bcopy((char *)server2->h_addr,
	  (char *)&serveraddr2.sin_addr.s_addr, server2->h_length);
    serveraddr2.sin_port = htons(portno2);




    /* get message line from the user */
    //printf("Enter your hawk id: ");
    bzero(buf, BUFSIZE);
    //scanf("%s",buf);
    printf("WeatherClient %s\n",hawkid);



    /* send the message line to the server */
    n = write(sockfd, hawkid, strlen(hawkid));
    printf("'%s' sent to WeatherServer\n", hawkid);
    if (n < 0)
      error("ERROR writing to socket");



    /* print the server's reply */
    bzero(buf, BUFSIZE);
    memset(buf, 0, BUFSIZE);
    n = read(sockfd, buf, 1000);
    if (n < 0)
      error("ERROR reading from socket");
    printf("WeatherServer Says: '%s'\n", buf);

    /* UDP */
    while(quit == 0)
    {
        memset(buf, 0, BUFSIZE);
        memset(buf2, 0, BUFSIZE);
        memset(buf3, 0, BUFSIZE);
        memset(game, 0, strlen(game)+1);
        //printf("%s buf\n", buf);
        bzero(buf2, BUFSIZE);
        printf("Weather for which Big Ten team's game: ");
        scanf("%[^\n]%*c", buf2);
        //printf("%s" , buf);
        if(strcmp(buf2, "quit") == 0)
        {
            memcpy(buf3, buf2, strlen(buf2));

        }
        else
        {
            memcpy(buf3, hawkid, strlen(hawkid));
            memcpy(&buf3[strlen(hawkid)], space, strlen(space));
            sizing = strlen(hawkid) + strlen(space);
            memcpy(&buf3[sizing], buf2,  strlen(buf2));


        }


        /* send the message to the server */
        if((strcmp(buf3, "quit") == 0))
        {

            bzero(buf4, BUFSIZE);


            /* send the message line to the server */
            n = write(sockfd, q, strlen(q));
            printf("Request sent to WeatherServer: quit\n");
            if (n < 0)
              error("ERROR writing to socket");



            /* print the server's reply */
            bzero(buf4, BUFSIZE);
            memset(buf4, 0, BUFSIZE);
            n = read(sockfd, buf4, BUFSIZE);
            if (n < 0)
              error("ERROR reading from socket");
            printf("WeatherServer Says: '%s'\n", buf4);
            quit = 1;
            close(sockfd);
            close(sockfd2);

        }
        else
        {
            printf("'%s' sent to ScheduleServer\n", buf3);
            serverlen = sizeof(serveraddr2);
            n = sendto(sockfd2, buf3, strlen(buf3), 0, (struct sockaddr *)&serveraddr2, serverlen);
            if(n < 0)
              error("ERROR in sendto");


            /* print the server's reply */
            memset(buf3, 0, BUFSIZE);
            printf("buf3 has been reset to: %s\n", buf3);
            n = recvfrom(sockfd2, buf3, BUFSIZE, 0, (struct sockaddr *)&serveraddr2, &serverlen);
            if (n < 0)
              error("ERROR in recvfrom");
            printf("%s\n", buf3);
            memset(game, 0, strlen(game));
            game[0] = buf3[strlen(buf3) - 3];
            game[1] = buf3[strlen(buf3) - 2];
            game[2] = buf3[strlen(buf3) - 1];
            printf("'%s' sent to WeatherServer\n", game);
            bzero(buf4, BUFSIZE);

            /* send the message line to the server */
            n = write(sockfd, game, strlen(game));
            if (n < 0)
              error("ERROR writing to socket");



            /* print the server's reply */
            bzero(buf4, BUFSIZE);
	        memset(buf4, 0, BUFSIZE);
            n = read(sockfd, buf4, BUFSIZE);
            if (n < 0)
              error("ERROR reading from socket");
            printf("%s\n", buf4);
        }

    }
    return 0;
}

