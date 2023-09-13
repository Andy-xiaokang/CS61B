public class NBody {
    public static double readRadius(String file){
        In in = new In(file);
        int planet_number = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int planet_number = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[5];
        for (int i = 0; i < 5; i += 1){
            planets[i] = new Planet(0, 0, 0, 0, 0, "planet");
            planets[i].xxPos = in.readDouble();
            planets[i].yyPos = in.readDouble();
            planets[i].xxVel = in.readDouble();
            planets[i].yyVel = in.readDouble();
            planets[i].mass = in.readDouble();
            planets[i].imgFileName = in.readString();
        }
        return planets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]), dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeRadius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        // draw the background
        StdDraw.setXscale(-universeRadius, universeRadius);
        StdDraw.setYscale(-universeRadius, universeRadius);

        StdDraw.enableDoubleBuffering();

        double t = 0;
        int num = planets.length;
        while (t < T){
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for (int i = 0; i < num; i += 1){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < num; i += 1){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet : planets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            t = t + dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }

}
