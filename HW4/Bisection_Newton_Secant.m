function [ x_bisection, err_bisection, k_bisection, x_newton, err_newton, k_newton, x_secant, err_secant, k_secant ] = Bisection_Newton_Secant( f, df, x0, x1, tol, value )
%UNTITLED Summary of this function goes here
% Input
%   f - input funtion
%   df - derived input function
%   x0 - inicial approximation
%   x1 - initial approximation 2
%   tol - tolerance
%   value - test real value
% Output
%   x - approximation to root
%   err - error estimate
%   k - iteration time
tic

if nargin == 4
    tol = 1e-5;
    disp('No Newton method!')
elseif nargin == 5
    disp('No Newton method!')
elseif nargin ~= 6
    error('invalid input parameters');
end

f = inline(f);

% bisection method

if f(x0)*f(x1) > 0
    disp('Wrong choice')
else
    a = x0;
    b = x1;
    x_bisection = (a + b) / 2;
    err_bisection = b-a;
    k_bisection = 2;
    deltam(1) = err_bisection;
    disp(sprintf('\nBisection method: \n  iteration              x                 error             constant         convergence_rate      relative_error'))
    while (err_bisection > tol)
        if (f(a) * f(x_bisection) > 0)
            a = x_bisection;
        else
            b = x_bisection;
        end
        tmp = x_bisection;
        x_bisection = (a + b) / 2;
        err_bisection = (b-a) / 2;
        if (k_bisection-2 > 0)
            deltam(k_bisection) = abs(x_bisection - tmp);
            r = log(deltam(k_bisection)/deltam(k_bisection-1))/log(deltam(k_bisection-1)/deltam(k_bisection-2));
            c = deltam(k_bisection)/(deltam(k_bisection-1)^r);
        end
        relative_error_bisection = (x_bisection - value) / value;
        if (k_bisection-2 > 0)
            disp(sprintf('    %3d          %12.10e       %8.6e       %8.6e        %8.6e       %12.10e\n', k_bisection, x_bisection, err_bisection, c, r, relative_error_bisection))
        else
            disp(sprintf('    %3d          %12.10e       %8.6e                                               %12.10e\n', k_bisection, x_bisection, err_bisection, relative_error_bisection))
        end
        k_bisection = k_bisection + 1;
    end
end

% Newton's method
df = inline(df);
x_newton(1) = x0 - (f(x0)/df(x0));
err_newton(1) = abs(x_newton(1)-x0);
k_newton = 2;
disp(sprintf('Newton method: \n  iteration              x                 error             constant         convergence_rate       relative_error'))
while (err_newton(k_newton-1) > tol)
    tmp = x_newton(k_newton-1);
    x_newton(k_newton) = x_newton(k_newton-1) - (f(x_newton(k_newton-1))/df(x_newton(k_newton-1)));
    err_newton(k_newton) = abs(x_newton(k_newton)-x_newton(k_newton-1));
    deltax(k_newton) = abs(x_newton(k_newton) - tmp);
    if (k_newton > 2)
        r = log(deltax(k_newton)/deltax(k_newton-1))/log(deltax(k_newton-1)/deltax(k_newton-2));
        c = deltax(k_newton)/(deltax(k_newton-1)^r);
    end
    relative_error_newton = (x_newton(k_newton) - value) / value;
    if (k_newton-2 > 0)
        disp(sprintf('    %3d          %12.10e       %8.6e       %8.6e        %8.6e         %12.10e\n', k_newton, x_newton(k_newton), err_newton(k_newton), c, r, relative_error_newton))
    else
        disp(sprintf('    %3d          %12.10e       %8.6e                                                %12.10e\n', k_newton, x_newton(k_newton), err_newton(k_newton), relative_error_newton))
    end
    k_newton = k_newton+1;
end

% Secant method

x_secant(1) = x0;
x_secant(2) = x1;
k_secant = 2;
err_secant(2) = abs(x1-x0);
disp(sprintf('Secant method: \n  iteration              x                 error             constant        convergence_rate      relative_error'))
while (err_secant(k_secant) > tol)
    %tmp = x_secant(k_secant);
    x_secant(k_secant + 1) = x_secant(k_secant) - f(x_secant(k_secant)) * (x_secant(k_secant) - x_secant(k_secant - 1)) / (f(x_secant(k_secant)) - f(x_secant(k_secant-1)));
    err_secant(k_secant + 1) = abs(x_secant(k_secant + 1) - x_secant(k_secant));
    deltax(k_secant) = abs(x_secant(k_secant + 1) - x_secant(k_secant));
    if (k_secant > 2)
        r = log(deltax(k_secant)/deltax(k_secant-1))/log(deltax(k_secant-1)/deltax(k_secant-2));
        c = deltax(k_secant)/(deltax(k_secant-1)^r);
    end
    relative_error_secant = (x_secant(k_secant + 1) - value) / value;
    if (k_newton-2 > 0)
        disp(sprintf('    %3d          %12.10e       %8.6e       %8.6e        %8.6e        %12.10e\n', k_secant, x_secant(k_secant + 1), err_secant(k_secant + 1), c, r, relative_error_secant))
    end
    k_secant = k_secant + 1;
end

toc
end

